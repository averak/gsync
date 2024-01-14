package net.averak.gsync.adapter.handler.player_api.config

import io.grpc.BindableService
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import jakarta.annotation.PostConstruct
import net.averak.gsync.core.config.Config
import org.springframework.context.annotation.Configuration

@Configuration
open class PlayerApiServerConfig(
    private val config: Config,
    private val handlers: List<BindableService>,
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
        serverBuilder.build().start()
    }
}
