package net.averak.gsync.adapter.handler.admin_api

import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.core.logger.Logger
import org.apache.catalina.connector.ClientAbortException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Controller
@RestControllerAdvice
class GlobalRestControllerAdvice : ResponseEntityExceptionHandler() {

    @RequestMapping("/**")
    fun handleApiNotFound(): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Any> {
        return makeResponseAfterLogging(ex)
    }

    @ExceptionHandler(GsyncException::class)
    fun handleException(ex: GsyncException): ResponseEntity<Any> {
        return makeResponseAfterLogging(ex)
    }

    @ExceptionHandler(ClientAbortException::class)
    fun handleException(ex: ClientAbortException) {
        // クライアントがリクエストを中断した場合は警告ログを残し、処理を中断する
        Logger.warn(ex)
    }

    @SuppressWarnings("kotlin:S6510")
    private fun makeResponseAfterLogging(ex: Exception): ResponseEntity<Any> {
        val e = if (ex is GsyncException) {
            ex
        } else {
            GsyncException(ex)
        }

        when (e.errorCode) {
            ErrorCode.CLIENT_VERSION_IS_NOT_SUPPORTED -> {
                return ResponseEntity(ErrorResponse(e), HttpStatus.BAD_REQUEST)
            }

            else -> {
                Logger.error(e)
                return ResponseEntity(ErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }

    data class ErrorResponse(
        val code: String,
        val message: String,
    ) {

        constructor(ex: GsyncException) : this(ex.errorCode.name, ex.errorCode.summary)
    }
}
