package net.averak.gsync.adapter.handler.admin_api

import net.averak.gsync.usecase.EchoUsecase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping(path = ["/api/health"], produces = [MediaType.APPLICATION_JSON_VALUE])
class HealthCheckController(
    private val requestScope: HttpRequestScope,
    private val echoUsecase: EchoUsecase,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun healthCheck(): ResponseEntity<Response> {
        val gctx = requestScope.getGameContext()
        val result = echoUsecase.echo(gctx, "Health Check")

        return ResponseEntity.ok(Response(result.timestamp))
    }

    data class Response(
        val timestamp: LocalDateTime,
    )
}
