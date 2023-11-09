package net.averak.gsync.domain.model

import net.averak.gsync.domain.primitive.common.ID
import java.time.LocalDateTime

data class Echo(
    val id: ID,
    val message: String,
    val timestamp: LocalDateTime,
) {
    constructor(message: String) : this(
        ID(),
        message,
        LocalDateTime.now(),
    )
}
