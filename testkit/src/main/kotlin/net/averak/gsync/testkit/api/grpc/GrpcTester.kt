package net.averak.gsync.testkit.api.grpc

import com.google.protobuf.AbstractMessage
import io.grpc.*
import jakarta.annotation.PostConstruct
import net.averak.gsync.core.config.Config
import net.averak.gsync.domain.model.Platform
import net.averak.gsync.infrastructure.grpc.player_api.metadata.IncomingHeaderKey
import net.averak.gsync.schema.protobuf.player_api.EchoGrpc
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageGrpc
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class GrpcTester(
    private val config: Config,
) {

    companion object {

        private var metadata: MutableMap<IncomingHeaderKey, String> = mutableMapOf()

        private lateinit var channel: ManagedChannel

        lateinit var echo: EchoGrpc.EchoBlockingStub

        lateinit var playerStorage: PlayerStorageGrpc.PlayerStorageBlockingStub
    }

    @PostConstruct
    fun init() {
        channel = ManagedChannelBuilder.forAddress("localhost", config.playerApi.port)
            .usePlaintext()
            .intercept(MetadataInterceptor())
            .build()
        echo = EchoGrpc.newBlockingStub(channel)
        playerStorage = PlayerStorageGrpc.newBlockingStub(channel)
    }

    fun <REQ : AbstractMessage, RES : AbstractMessage> invoke(method: (REQ) -> RES, request: REQ): Response<RES> {
        try {
            val response = method(request)
            return Response(Status.OK, response)
        } catch (ex: StatusRuntimeException) {
            return Response(ex.status, null)
        } finally {
            metadata.clear()
        }
    }

    fun <REQ : AbstractMessage, RES : AbstractMessage> invoke(
        method: (REQ) -> RES,
        request: REQ,
        option: MetadataBuilder.() -> Any,
    ): Response<RES> {
        option(MetadataBuilder())
        return invoke(method, request)
    }

    class MetadataBuilder {

        fun session(playerID: UUID, gameID: UUID) {
            metadata[IncomingHeaderKey.DEBUG_SPOOFING_PLAYER_ID] = playerID.toString()
            metadata[IncomingHeaderKey.GAME_ID] = gameID.toString()
        }

        fun client(version: String, platform: Platform) {
            metadata[IncomingHeaderKey.CLIENT_VERSION] = version
            metadata[IncomingHeaderKey.PLATFORM] = platform.name
        }

        fun gameID(value: UUID) {
            metadata[IncomingHeaderKey.GAME_ID] = value.toString()
        }

        fun spoofingPlayerID(value: UUID) {
            metadata[IncomingHeaderKey.DEBUG_SPOOFING_PLAYER_ID] = value.toString()
        }

        fun spoofingMasterVersion(value: UUID) {
            metadata[IncomingHeaderKey.DEBUG_SPOOFING_MASTER_VERSION] = value.toString()
        }

        fun spoofingCurrentTime(value: LocalDateTime) {
            metadata[IncomingHeaderKey.DEBUG_SPOOFING_CURRENT_TIME] = value.toString()
        }
    }

    private class MetadataInterceptor : ClientInterceptor {

        override fun <ReqT : Any, RespT : Any> interceptCall(
            method: MethodDescriptor<ReqT, RespT>,
            callOptions: CallOptions,
            next: Channel,
        ): ClientCall<ReqT, RespT> {
            val newCall = next.newCall(method, callOptions)

            return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(newCall) {
                override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                    metadata.forEach { (k, v) ->
                        headers.put(Metadata.Key.of(k.key, Metadata.ASCII_STRING_MARSHALLER), v)
                    }
                    super.start(responseListener, headers)
                }
            }
        }
    }
}
