package net.averak.gsync.testkit.api.grpc

import com.google.protobuf.GeneratedMessageV3
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils
import jakarta.annotation.PostConstruct
import net.averak.gsync.adapter.handler.player_api.mdval.IncomingHeaderKey
import net.averak.gsync.core.config.Config
import net.averak.gsync.schema.protobuf.player_api.EchoGrpc
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageGrpc
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class GrpcTester(
    private val config: Config,
) {

    private var metadata: MutableMap<IncomingHeaderKey, String> = mutableMapOf()

    private lateinit var channel: ManagedChannel

    lateinit var echo: EchoGrpc.EchoBlockingStub

    lateinit var playerStorage: PlayerStorageGrpc.PlayerStorageBlockingStub

    @PostConstruct
    fun init() {
        channel = ManagedChannelBuilder.forAddress("localhost", config.playerApi.port).usePlaintext().build()
    }

    private fun initStubs() {
        val extraHeaders = Metadata()
        metadata.forEach { (k, v) ->
            extraHeaders.put(Metadata.Key.of(k.key, Metadata.ASCII_STRING_MARSHALLER), v)
        }
        val interceptor = MetadataUtils.newAttachHeadersInterceptor(extraHeaders)

        echo = EchoGrpc.newBlockingStub(channel).withInterceptors(interceptor)
        playerStorage = PlayerStorageGrpc.newBlockingStub(channel).withInterceptors(interceptor)
    }

    fun <REQ : GeneratedMessageV3, RES : GeneratedMessageV3> invoke(method: (REQ) -> RES, request: REQ): RES {
        val response = method(request)
        metadata.clear()
        return response
    }

    fun withSession(playerID: UUID, gameID: UUID) {
        metadata[IncomingHeaderKey.DEBUG_SPOOFING_PLAYER_ID] = playerID.toString()
        metadata[IncomingHeaderKey.GAME_ID] = gameID.toString()
        initStubs()
    }

    fun withGameID(value: UUID) {
        metadata[IncomingHeaderKey.GAME_ID] = value.toString()
        initStubs()
    }

    fun withSpoofingPlayerID(value: UUID) {
        metadata[IncomingHeaderKey.DEBUG_SPOOFING_PLAYER_ID] = value.toString()
        initStubs()
    }

    fun withSpoofingMasterVersion(value: UUID) {
        metadata[IncomingHeaderKey.DEBUG_SPOOFING_MASTER_VERSION] = value.toString()
        initStubs()
    }

    fun withSpoofingCurrentTime(value: LocalDateTime) {
        metadata[IncomingHeaderKey.DEBUG_SPOOFING_CURRENT_TIME] = value.toString()
        initStubs()
    }
}
