package net.averak.gsync.adapter.handler.player_api.pbconv

import com.google.protobuf.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeConverter {

    companion object {

        fun toPb(localDateTime: LocalDateTime): Timestamp {
            return Timestamp.newBuilder()
                .setSeconds(localDateTime.atZone(ZoneOffset.systemDefault()).toEpochSecond())
                .setNanos(localDateTime.nano)
                .build()
        }
    }
}
