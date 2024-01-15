package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.handler.player_api.pbconv.PlayerStorageConverter
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture

import java.time.LocalDateTime

class PlayerStorageHandler_SearchV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを取得できる"() {
        given:
        final now = LocalDateTime.now()
        final message = Faker.alphanumeric()

        when:
        final request = EchoEchoV1.Request.newBuilder().setMessage(message).build()
        final response = grpcTester.echo.echoV1(request)

        then:
        response.message == message
        with(sql.rows("SELECT * FROM gsync_echo")) {
            it.size() == 1
            it[0].message == message
            Assert.timestampIs(it[0].timestamp, now)
        }
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

        when:
        final entry = Faker.fake(PlayerStorageEntry)
        final request = PlayerStorageSetV1.Request.newBuilder()
            .setEntry(PlayerStorageConverter.toPb(entry))
            .setPreviousRevision("00000000-0000-0000-0000-000000000000")
            .build()
        final response = grpcTester.playerStorage.setV1(request)

        then:
        response.entry.key == entry.key
        response.entry.value.toByteArray() == entry.value

        with(sql.rows("SELECT * FROM gsync_player_storage_revision")) {
            it.size() == 1
            it[0].player_storage_revision_id == response.nextRevision
        }
        with(sql.rows("SELECT * FROM gsync_player_storage_entry")) {
            it.size() == 1
            it[0].key == entry.key
            it[0].value == entry.value
        }
    }
}

class PlayerStorageHandler_ClearV1_IT extends AbstractDatabaseSpec {

    def "正常系: パターンにマッチしたエントリを削除できる"() {}

}

