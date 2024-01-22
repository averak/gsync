package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker

import java.time.LocalDateTime

class EchoHandler_EchoV1_IT extends AbstractDatabaseSpec {

    def "正常系"() {
        given:
        final now = LocalDateTime.now()
        final message = Faker.alphanumeric()

        when:
        final response = grpcTester.invoke(
            grpcTester.echo.&echoV1,
            EchoEchoV1.Request.newBuilder().setMessage(message).build(),
        ) {
            it.spoofingMasterVersion(Faker.uuidv4())
            it.spoofingCurrentTime(now)
        }

        then:
        response.message.message == message
        Assert.timestampIs(response.message.timestamp, now)
        with(sql.rows("SELECT * FROM gsync_echo")) {
            it.size() == 1
            it[0].message == message
            Assert.timestampIs(it[0].timestamp, now)
        }
    }
}
