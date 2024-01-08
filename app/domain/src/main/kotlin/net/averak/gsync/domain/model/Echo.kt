package net.averak.gsync.domain.model

import java.time.LocalDateTime
import java.util.*

data class Echo(
    val id: UUID,
    val message: String,
    val timestamp: LocalDateTime,
) {

    constructor(message: String, now: LocalDateTime) : this(
        UUID.randomUUID(),
        message,
        now,
    )
}
