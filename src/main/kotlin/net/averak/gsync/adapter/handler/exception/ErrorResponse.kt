package net.averak.gsync.adapter.handler.exception

import net.averak.gsync.core.exception.ErrorCode

data class ErrorResponse(
    val code: String,
    val description: String,
) {
    constructor(errorCode: ErrorCode) : this(errorCode.name, errorCode.description)
}
