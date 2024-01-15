package net.averak.gsync.testkit.api.rest

import net.averak.gsync.adapter.handler.admin_api.GlobalRestControllerAdvice
import org.springframework.http.HttpStatus

data class Response<T>(
    val status: HttpStatus,
    val body: T?,
    val error: GlobalRestControllerAdvice.ErrorResponse?,
)
