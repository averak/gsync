package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionDto
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.domain.repository.exception.AlreadyDoneException
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

import java.time.LocalDateTime

class PlayerStorageRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    PlayerStorageRepository sut

    @Shared
    LocalDateTime now = LocalDateTime.of(2999, 1, 1, 0, 0, 0)

    def "get: プレイヤーストレージを取得できる"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                "playerId": Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageRevisionDto, [
                "playerId"               : Faker.uuidv5("p1").toString(),
                "gameId"                 : Faker.uuidv5("g1").toString(),
                "playerStorageRevisionId": Faker.uuidv5("outdated revision").toString(),
                "createdAt"              : LocalDateTime.of(2000, 1, 1, 0, 0, 0),
            ]),
            Faker.fake(PlayerStorageRevisionDto, [
                "playerId"               : Faker.uuidv5("p1").toString(),
                "gameId"                 : Faker.uuidv5("g1").toString(),
                "playerStorageRevisionId": Faker.uuidv5("latest revision").toString(),
                "createdAt"              : LocalDateTime.of(2000, 1, 1, 0, 0, 1),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageEntryDto, [
                "playerId": Faker.uuidv5("p1").toString(),
                "gameId"  : Faker.uuidv5("g1").toString(),
                "key"     : "group1#key1",
                "value"   : "value1".bytes,
            ]),
            Faker.fake(PlayerStorageEntryDto, [
                "playerId": Faker.uuidv5("p1").toString(),
                "gameId"  : Faker.uuidv5("g1").toString(),
                "key"     : "group1#key2",
                "value"   : "value2".bytes,
            ]),
            Faker.fake(PlayerStorageEntryDto, [
                "playerId": Faker.uuidv5("p1").toString(),
                "gameId"  : Faker.uuidv5("g1").toString(),
                "key"     : "group2#key1",
                "value"   : "value3".bytes,
            ]),
            Faker.fake(PlayerStorageEntryDto, [
                "playerId": Faker.uuidv5("p1").toString(),
                "gameId"  : Faker.uuidv5("g1").toString(),
                "key"     : "group2#key2",
                "value"   : "value4".bytes,
            ]),
        )

        when:
        final actual = sut.get(Faker.fake(GameContext), playerID, gameID, criteria)

        then:
        actual == expected

        where:
        playerID           | gameID             | criteria                                                                               || expected
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria(["group1#key1"], [])                || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [new PlayerStorageEntry("group1#key1", "value1".bytes)])
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria(["group1#key1", "group1#key2"], []) || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [new PlayerStorageEntry("group1#key1", "value1".bytes), new PlayerStorageEntry("group1#key2", "value2".bytes)])
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria([], ["group2"])                     || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [new PlayerStorageEntry("group2#key1", "value3".bytes), new PlayerStorageEntry("group2#key2", "value4".bytes)])
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria(["group1#key1"], ["group2"])        || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [new PlayerStorageEntry("group1#key1", "value1".bytes), new PlayerStorageEntry("group2#key1", "value3".bytes), new PlayerStorageEntry("group2#key2", "value4".bytes)])
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria(["group3#key1"], [])                || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [])
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria([], ["group3"])                     || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [])
        Faker.uuidv5("p1") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria([], [])                             || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g1"), Faker.uuidv5("latest revision"), [])
        // 以下、リビジョンが存在しないケース
        Faker.uuidv5("p2") | Faker.uuidv5("g1") | new IPlayerStorageRepository.PlayerStorageCriteria([], [])                             || new PlayerStorage(Faker.uuidv5("p2"), Faker.uuidv5("g1"), UUID.fromString("00000000-0000-0000-0000-000000000000"), [])
        Faker.uuidv5("p1") | Faker.uuidv5("g2") | new IPlayerStorageRepository.PlayerStorageCriteria([], [])                             || new PlayerStorage(Faker.uuidv5("p1"), Faker.uuidv5("g2"), UUID.fromString("00000000-0000-0000-0000-000000000000"), [])
    }

    def "save: プレイヤーストレージを保存できる"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                "playerId": Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageRevisionDto, [
                "playerId"               : Faker.uuidv5("p1").toString(),
                "gameId"                 : Faker.uuidv5("g1").toString(),
                "playerStorageRevisionId": Faker.uuidv5("r1").toString(),
                "idempotencyKey"         : Faker.uuidv5("i1").toString(),
                "createdAt"              : LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                "updatedAt"              : LocalDateTime.of(2000, 1, 1, 0, 0, 0),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageEntryDto, [
                "playerId" : Faker.uuidv5("p1").toString(),
                "gameId"   : Faker.uuidv5("g1").toString(),
                "key"      : "key1",
                "value"    : "value1".bytes,
                "createdAt": LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                "updatedAt": LocalDateTime.of(2000, 1, 1, 0, 0, 0),
            ]),
        )

        when:
        sut.save(testcase.when.gctx, testcase.when.playerStorage)

        then:
        testcase.then()

        where:
        testcase << [
            [
                name: "レコードが存在しない場合、作成される",
                when: [
                    gctx         : Faker.fake(GameContext, [
                        currentTime   : now,
                        idempotencyKey: Faker.uuidv5("i2"),
                    ]),
                    playerStorage: Faker.fake(PlayerStorage, [
                        playerID: Faker.uuidv5("p1"),
                        gameID  : Faker.uuidv5("g1"),
                        revision: Faker.uuidv5("r2"),
                        entries : [
                            Faker.fake(PlayerStorageEntry, [
                                key  : "key2",
                                value: "value2".bytes,
                            ]),
                        ],
                    ]),
                ],
                then: () -> {
                    with(sql.rows("SELECT * FROM gsync_player_storage_revision ORDER BY created_at")) {
                        it*.player_storage_revision_id == [Faker.uuidv5("r1").toString(), Faker.uuidv5("r2").toString()]
                        Assert.timestampIs(it[1].created_at, now)
                        Assert.timestampIs(it[1].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_storage_entry ORDER BY created_at")) {
                        it*.key == ["key1", "key2"]
                        it*.value == ["value1".bytes, "value2".bytes]
                        Assert.timestampIs(it[1].created_at, now)
                        Assert.timestampIs(it[1].updated_at, now)
                    }
                    return true
                },
            ],
            [
                name: "レコードが存在する場合、更新される",
                when: [
                    gctx         : Faker.fake(GameContext, [
                        currentTime   : now,
                        idempotencyKey: Faker.uuidv5("i2"),
                    ]),
                    playerStorage: Faker.fake(PlayerStorage, [
                        playerID: Faker.uuidv5("p1"),
                        gameID  : Faker.uuidv5("g1"),
                        revision: Faker.uuidv5("r1"),
                        entries : [
                            Faker.fake(PlayerStorageEntry, [
                                key  : "key1",
                                value: "new value".bytes,
                            ]),
                        ],
                    ]),
                ],
                then: () -> {
                    with(sql.rows("SELECT * FROM gsync_player_storage_revision")) {
                        it.size() == 1
                        it[0].player_storage_revision_id == Faker.uuidv5("r1").toString()
                        Assert.timestampIs(it[0].created_at, LocalDateTime.of(2000, 1, 1, 0, 0, 0))
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_storage_entry")) {
                        it.size() == 1
                        it[0].key == "key1"
                        it[0].value == "new value".bytes
                        Assert.timestampIs(it[0].created_at, LocalDateTime.of(2000, 1, 1, 0, 0, 0))
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    return true
                },
            ],
            [
                name: "バイナリが空のエントリは削除される",
                when: [
                    gctx         : Faker.fake(GameContext, [
                        currentTime   : now,
                        idempotencyKey: Faker.uuidv5("i2"),
                    ]),
                    playerStorage: Faker.fake(PlayerStorage, [
                        playerID: Faker.uuidv5("p1"),
                        gameID  : Faker.uuidv5("g1"),
                        revision: Faker.uuidv5("r1"),
                        entries : [
                            Faker.fake(PlayerStorageEntry, [
                                key  : "key1",
                                value: new byte[0],
                            ]),
                            // 存在しないキーを指定してもエラーにならない
                            Faker.fake(PlayerStorageEntry, [
                                key  : "key2",
                                value: new byte[0],
                            ]),
                        ],
                    ]),
                ],
                then: () -> {
                    with(sql.rows("SELECT * FROM gsync_player_storage_entry")) {
                        it.size() == 0
                    }
                    return true
                },
            ],
        ]
    }

    def "save: 同じ冪等キーの更新履歴が存在する場合はエラーを返す"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                "playerId": Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageRevisionDto, [
                "playerId"               : Faker.uuidv5("p1").toString(),
                "gameId"                 : Faker.uuidv5("g1").toString(),
                "playerStorageRevisionId": Faker.uuidv5("r1").toString(),
                "idempotencyKey"         : Faker.uuidv5("i1").toString(),
            ]),
        )

        when:
        final gctx = Faker.fake(GameContext, [idempotencyKey: Faker.uuidv5("i1"),])
        final playerStorage = Faker.fake(PlayerStorage, [
            playerID: Faker.uuidv5("p1"),
            gameID  : Faker.uuidv5("g1"),
            revision: Faker.uuidv5("r1"),
            entries : [
                Faker.fake(PlayerStorageEntry, [
                    key  : "key1",
                    value: "new value".bytes,
                ]),
            ],
        ])
        sut.save(gctx, playerStorage)

        then:
        thrown(AlreadyDoneException)
    }
}
