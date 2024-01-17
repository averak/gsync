package net.averak.gsync.adapter.handler.player_api.interceptor

import com.google.protobuf.GeneratedMessageV3
import com.google.protobuf.util.JsonFormat
import io.grpc.*
import net.averak.gsync.adapter.handler.player_api.mdval.OutgoingTrailerKey
import net.averak.gsync.adapter.handler.player_api.mdval.RequestScope
import net.averak.gsync.core.logger.Logger
import net.averak.gsync.infrastructure.json.JsonUtils
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime

@Component
@Order(1)
class AccessLogInterceptor(
    private val logger: Logger,
    private val requestScope: RequestScope,
) : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>,
    ): ServerCall.Listener<ReqT> {
        val begin = LocalDateTime.now()
        return next.startCall(
            object : ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {

                lateinit var end: LocalDateTime

                @Suppress("kotlin:S108")
                override fun sendMessage(message: RespT) {
                    end = LocalDateTime.now()
                    val logMessage = mutableMapOf<String, Any?>()
                    logMessage["method"] = call.methodDescriptor.fullMethodName
                    logMessage["requested_at"] = begin.toString()
                    logMessage["elapsed_ms"] = Duration.between(begin, end).toMillis()
                    logMessage["metadata"] = headers.keys().associateWith {
                        headers[Metadata.Key.of(it, Metadata.ASCII_STRING_MARSHALLER)]
                    }
                    if (message is GeneratedMessageV3) {
                        logMessage["response"] = JsonUtils.decode(
                            JsonFormat.printer().print(message),
                            Map::class.java,
                        )
                    }
                    val hint = mutableMapOf<String, Any>()
                    try {
                        hint["game_context"] = requestScope.gctx
                        hint["auth_context"] = mapOf(
                            "player_id" to requestScope.playerID,
                            "game_id" to requestScope.gameID,
                        )
                    } catch (_: NullPointerException) {
                    }
                    logMessage["hint"] = hint

                    logger.info("access log", logMessage)
                    super.sendMessage(message)
                }

                override fun close(status: Status, trailers: Metadata) {
                    trailers.put(
                        Metadata.Key.of(OutgoingTrailerKey.RESPOND_TIMESTAMP.key, Metadata.ASCII_STRING_MARSHALLER),
                        end.toString(),
                    )
                    super.close(status, trailers)
                }
            },
            headers,
        )
    }
}
