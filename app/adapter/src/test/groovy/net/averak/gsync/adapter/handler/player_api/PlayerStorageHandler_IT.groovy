package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionDto
import net.averak.gsync.adapter.handler.player_api.pbconv.PlayerStorageConverter
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.schema.protobuf.resource.player_storage.Criteria
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture

class PlayerStorageHandler_SearchV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを取得できる"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                "playerId": Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageRevisionDto, [
                "playerId": Faker.uuidv5("p1").toString(),
                "gameId"  : Faker.uuidv5("g1").toString(),
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
        )

        when:
        final response = grpcTester.invoke(
            grpcTester.playerStorage.&searchV1,
            PlayerStorageSearchV1.Request.newBuilder()
                .setCriteria(Criteria.newBuilder()
                    .setPattern("group1")
                    .setMatchingType(Criteria.MatchingType.FORWARD_MATCH)
                    .build()
                ).build(),
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(Faker.uuidv4())
        }

        then:
        response.message.entriesList*.key == ["group1#key1", "group1#key2"]
        response.message.entriesList*.value*.toByteArray() == ["value1".bytes, "value2".bytes]
    }
}

class PlayerStorageHandler_SetV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを作成できる"() {
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
            ]),
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
            it.spoofingMasterVersion(Faker.uuidv4())
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
        Fixture.setup(
            Faker.fake(PlayerDto, [
                "playerId": Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerStorageRevisionDto, [
                "playerId"               : Faker.uuidv5("p1").toString(),
                "gameId"                 : Faker.uuidv5("g1").toString(),
                "playerStorageRevisionId": Faker.uuidv5("current revision").toString(),
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
        )

        when:
        final response = grpcTester.invoke(
            grpcTester.playerStorage.&clearV1,
            PlayerStorageClearV1.Request.newBuilder()
                .setPreviousRevision(Faker.uuidv5("current revision").toString())
                .setCriteria(Criteria.newBuilder()
                    .setPattern("group1")
                    .setMatchingType(Criteria.MatchingType.FORWARD_MATCH)
                    .build()
                ).build(),
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(Faker.uuidv4())
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

