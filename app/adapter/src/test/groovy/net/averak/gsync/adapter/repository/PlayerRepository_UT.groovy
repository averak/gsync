package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.dao.dto.base.PlayerLoginDto
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileDto
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Player
import net.averak.gsync.domain.model.PlayerLogin
import net.averak.gsync.domain.model.PlayerProfile
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

import java.time.LocalDateTime

class PlayerRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    PlayerRepository sut

    @Shared
    LocalDateTime now = LocalDateTime.now()

    def "get: プレイヤーを取得できる"() {
        given:
        Fixture.setup(Faker.fake(PlayerDto, [
            playerId: Faker.uuidv5("p1").toString(),
        ]))
        Fixture.setup(Faker.fake(PlayerProfileDto, [
            playerId: Faker.uuidv5("p1").toString(),
        ]))

        testcase.given()

        when:
        final result = this.sut.get(Faker.fake(GameContext), testcase.when.id)

        then:
        testcase.then(result)

        where:
        testcase << [
            [
                name : "ログイン情報が存在する場合、ログイン情報を取得できる",
                given: () -> {
                    Fixture.setup(Faker.fake(PlayerLoginDto, [
                        playerId: Faker.uuidv5("p1").toString(),
                    ]))
                },
                when : [
                    id: Faker.uuidv5("p1"),
                ],
                then : (Player p) -> {
                    assert p != null
                    assert p.id == Faker.uuidv5("p1")
                    assert p.profile != null
                    assert p.login != null
                    return true
                },
            ],
            [
                name : "ログイン情報が存在しない場合、ログイン情報は NULL になる",
                given: () -> { },
                when : [
                    id: Faker.uuidv5("p1"),
                ],
                then : (Player p) -> {
                    assert p != null
                    assert p.id == Faker.uuidv5("p1")
                    assert p.profile != null
                    assert p.login == null
                    return true
                },
            ],
            [
                name : "IDが存在しない場合、NULL が返る",
                given: () -> { },
                when : [
                    id: Faker.uuidv5("p2"),
                ],
                then : (Player p) -> {
                    assert p == null
                    return true
                },
            ],
        ]
    }

    def "save: 保存できる"() {
        given:
        testcase.given()

        when:
        final gctx = Faker.fake(GameContext, [currentTime: now])
        this.sut.save(gctx, testcase.when.player)

        then:
        testcase.then()

        where:
        testcase << [
            [
                name : "レコードが存在する場合、更新される",
                given: () -> {
                    Fixture.setup(Faker.fake(PlayerDto, [
                        playerId : Faker.uuidv5("p1").toString(),
                        isBanned : false,
                        createdAt: now.minusDays(1),
                    ]))
                    Fixture.setup(Faker.fake(PlayerProfileDto, [
                        playerId : Faker.uuidv5("p1").toString(),
                        createdAt: now.minusDays(1),
                    ]))
                    Fixture.setup(Faker.fake(PlayerLoginDto, [
                        playerId : Faker.uuidv5("p1").toString(),
                        createdAt: now.minusDays(1),
                    ]))
                },
                when : [
                    player: Faker.fake(Player, [
                        id      : Faker.uuidv5("p1"),
                        isBanned: true,
                        profile : Faker.fake(PlayerProfile, [
                            nickname: "new nickname",
                        ]),
                        login   : Faker.fake(PlayerLogin, [
                            totalLoginDays: 100,
                        ]),
                    ]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_player")) {
                        assert it.size() == 1
                        assert it[0].is_banned == true
                        Assert.timestampIs(it[0].created_at, now.minusDays(1))
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_profile")) {
                        assert it.size() == 1
                        assert it[0].nickname == "new nickname"
                        Assert.timestampIs(it[0].created_at, now.minusDays(1))
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_login")) {
                        assert it.size() == 1
                        assert it[0].total_login_days == 100
                        Assert.timestampIs(it[0].created_at, now.minusDays(1))
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    return true
                },
            ],
            [
                name : "レコードが存在しない場合、作成される",
                given: () -> { },
                when : [
                    player: Faker.fake(Player, [
                        id: Faker.uuidv5("p1"),
                    ]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_player")) {
                        assert it.size() == 1
                        assert it[0].player_id == Faker.uuidv5("p1").toString()
                        Assert.timestampIs(it[0].created_at, now)
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_profile")) {
                        assert it.size() == 1
                        assert it[0].player_id == Faker.uuidv5("p1").toString()
                        Assert.timestampIs(it[0].created_at, now)
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_login")) {
                        assert it.size() == 1
                        assert it[0].player_id == Faker.uuidv5("p1").toString()
                        Assert.timestampIs(it[0].created_at, now)
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    return true
                },
            ],
            [
                name : "ログイン履歴が NULL の場合はログイン履歴の作成をスキップする",
                given: () -> { },
                when : [
                    player: Faker.fake(Player, [
                        id   : Faker.uuidv5("p1"),
                        login: null,
                    ]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_player")) {
                        assert it.size() == 1
                        assert it[0].player_id == Faker.uuidv5("p1").toString()
                        Assert.timestampIs(it[0].created_at, now)
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_profile")) {
                        assert it.size() == 1
                        assert it[0].player_id == Faker.uuidv5("p1").toString()
                        Assert.timestampIs(it[0].created_at, now)
                        Assert.timestampIs(it[0].updated_at, now)
                    }
                    with(sql.rows("SELECT * FROM gsync_player_login")) {
                        assert it.size() == 0
                    }
                    return true
                },
            ],
        ]
    }
}
