package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.handler.player_api.pbconv.PlayerStorageConverter
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.schema.protobuf.resource.player_storage.Criteria
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.fixture.builder.player.PlayerDataBuilder
import net.averak.gsync.testkit.fixture.builder.player.PlayerStorageBuilder

class PlayerStorageHandler_SearchV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを取得できる"() {
        given:
        final gctx = Faker.fake(GameContext)
        playerUp.setup(
            gctx,
            new PlayerDataBuilder(Faker.uuidv5("p1"))
                .playerStorage(
                    new PlayerStorageBuilder(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
                        .entries(
                            new PlayerStorageEntry("group1#key1", "value1".bytes),
                            new PlayerStorageEntry("group1#key2", "value2".bytes),
                            new PlayerStorageEntry("group2#key1", "value3".bytes),
                        )
                        .build(),
                ).build()
        )

        when:
        final response = grpcTester.invoke(
            grpcTester.playerStorage.&searchV1,
            PlayerStorageSearchV1.Request.newBuilder()
                .setCriteria(Criteria.newBuilder()
                    .setPattern("group1")
                    .setMatchingType(Criteria.MatchingType.MATCHING_TYPE_FORWARD_MATCH)
                    .build()
                ).build(),
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(gctx.masterVersion)
        }

        then:
        response.message.entriesList*.key == ["group1#key1", "group1#key2"]
        response.message.entriesList*.value*.toByteArray() == ["value1".bytes, "value2".bytes]
    }
}

class PlayerStorageHandler_SetV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを作成できる"() {
        given:
        final gctx = Faker.fake(GameContext)
        playerUp.setup(
            gctx,
            new PlayerDataBuilder(Faker.uuidv5("p1"))
                .playerStorage(
                    new PlayerStorageBuilder(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
                        .revision(Faker.uuidv5("r1"))
                        .entries()
                        .build(),
                ).build()
        )

        final entry = Faker.fake(PlayerStorageEntry)

        when:
        final response = grpcTester.invoke(
            grpcTester.playerStorage.&setV1,
            PlayerStorageSetV1.Request.newBuilder()
                .setEntry(PlayerStorageConverter.toPb(entry))
                .setPreviousRevision(Faker.uuidv5("r1").toString())
                .build(),
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(gctx.masterVersion)
        }

        then:
        response.message.entry.key == entry.key
        response.message.entry.value.toByteArray() == entry.value

        with(sql.rows("SELECT * FROM gsync_player_storage_revision")) {
            it.size() == 2
            it.player_storage_revision_id.sort() == [Faker.uuidv5("r1").toString(), response.message.nextRevision].sort()
        }
        with(sql.rows("SELECT * FROM gsync_player_storage_entry")) {
            it.size() == 1
            it[0].key == entry.key
            it[0].value == entry.value
        }
    }
}

class PlayerStorageHandler_ClearV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを削除できる"() {
        given:
        final gctx = Faker.fake(GameContext)
        playerUp.setup(
            gctx,
            new PlayerDataBuilder(Faker.uuidv5("p1"))
                .playerStorage(
                    new PlayerStorageBuilder(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
                        .revision(Faker.uuidv5("current revision"))
                        .entries(
                            new PlayerStorageEntry("group1#key1", "value1".bytes),
                            new PlayerStorageEntry("group1#key2", "value2".bytes),
                            new PlayerStorageEntry("group2#key1", "value3".bytes),
                        )
                        .build(),
                ).build()
        )

        when:
        final response = grpcTester.invoke(
            grpcTester.playerStorage.&clearV1,
            PlayerStorageClearV1.Request.newBuilder()
                .setPreviousRevision(Faker.uuidv5("current revision").toString())
                .setCriteria(Criteria.newBuilder()
                    .setPattern("group1")
                    .setMatchingType(Criteria.MatchingType.MATCHING_TYPE_FORWARD_MATCH)
                    .build()
                ).build(),
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(gctx.masterVersion)
        }

        then:
        response.message.nextRevision != Faker.uuidv5("current revision").toString()
        with(sql.rows("SELECT * FROM gsync_player_storage_revision")) {
            it.size() == 2
            it*.player_storage_revision_id.sort() == [Faker.uuidv5("current revision").toString(), response.message.nextRevision].sort()
        }
        with(sql.rows("SELECT * FROM gsync_player_storage_entry")) {
            it.size() == 1
            it[0].key == "group2#key1"
        }
    }
}

