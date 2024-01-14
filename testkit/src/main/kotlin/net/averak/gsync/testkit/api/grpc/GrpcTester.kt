package net.averak.gsync.testkit.api.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import jakarta.annotation.PostConstruct
import net.averak.gsync.core.config.Config
import net.averak.gsync.schema.protobuf.player_api.EchoGrpc
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageGrpc
import org.springframework.stereotype.Component

@Component
class GrpcTester(
    private val config: Config,
) {

    private lateinit var channel: ManagedChannel

    lateinit var echo: EchoGrpc.EchoBlockingStub

    lateinit var playerStorage: PlayerStorageGrpc.PlayerStorageBlockingStub

    @PostConstruct
    fun init() {
        channel = ManagedChannelBuilder.forAddress("localhost", config.playerApi.port).usePlaintext().build()
        echo = EchoGrpc.newBlockingStub(channel)
        playerStorage = PlayerStorageGrpc.newBlockingStub(channel)
    }
}
