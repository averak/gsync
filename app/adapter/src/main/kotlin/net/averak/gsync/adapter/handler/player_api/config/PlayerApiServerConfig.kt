package net.averak.gsync.adapter.handler.player_api.config

import io.grpc.BindableService
import io.grpc.ServerBuilder
import io.grpc.ServerInterceptor
import io.grpc.protobuf.services.ProtoReflectionService
import jakarta.annotation.PostConstruct
import net.averak.gsync.core.config.Config
import org.springframework.context.annotation.Configuration

@Configuration
open class PlayerApiServerConfig(
    private val config: Config,
    private val handlers: List<BindableService>,
    private val interceptors: List<ServerInterceptor>,
) {

    @PostConstruct
    fun start() {
        val serverBuilder = ServerBuilder.forPort(config.playerApi.port)
        if (config.debug) {
            serverBuilder.addService(ProtoReflectionService.newInstance())
        }
        handlers.forEach {
            serverBuilder.addService(it)
        }
        interceptors.forEach {
            serverBuilder.intercept(it)
        }
        serverBuilder.build().start()
    }
}
