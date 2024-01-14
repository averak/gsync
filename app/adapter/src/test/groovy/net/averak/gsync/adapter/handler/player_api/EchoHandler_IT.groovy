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
