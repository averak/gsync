package net.averak.gsync.adapter.handler.rest

import net.averak.gsync.usecase.EchoUsecase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/health"], produces = [MediaType.APPLICATION_JSON_VALUE])
class HealthCheckController(
    private val requestScope: HttpRequestScope,
    private val echoUsecase: EchoUsecase,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun healthCheck() {
        val gctx = requestScope.getGameContext()
        echoUsecase.echo(gctx, "Health Check")
    }
}
