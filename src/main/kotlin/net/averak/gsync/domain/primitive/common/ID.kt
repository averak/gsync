package net.averak.gsync.domain.primitive.common

import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import java.util.UUID

data class ID(val value: String) {
    init {
        try {
            UUID.fromString(value)
        } catch (_: IllegalArgumentException) {
            throw GsyncException(ErrorCode.ID_FORMAT_IS_INVALID)
        }
    }

    constructor() : this(UUID.randomUUID().toString())
}
