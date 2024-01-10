package net.averak.gsync.adapter.pbconv

import net.averak.gsync.domain.model.Echo
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1

class EchoConverter {

    companion object {

        fun toPb(echo: Echo): EchoEchoV1.Response {
            return EchoEchoV1.Response.newBuilder()
                .setTimestamp(LocalDateTimeConverter.toPb(echo.timestamp))
                .setMessage(echo.message).build()
        }
    }
}
