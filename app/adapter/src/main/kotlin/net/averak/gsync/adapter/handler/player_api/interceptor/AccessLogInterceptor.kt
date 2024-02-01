package net.averak.gsync.adapter.handler.player_api.interceptor

import com.google.protobuf.GeneratedMessageV3
import com.google.protobuf.util.JsonFormat
import io.grpc.*
import net.averak.gsync.core.logger.Logger
import net.averak.gsync.infrastructure.grpc.player_api.metadata.RequestScope
import net.averak.gsync.infrastructure.json.JsonUtils
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Order(Int.MAX_VALUE)
class AccessLogInterceptor(
    private val requestScope: RequestScope,
) : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>,
    ): ServerCall.Listener<ReqT> {
        val begin = LocalDateTime.now()
        val requestDetail = mutableMapOf<String, Any?>()
        requestDetail["method"] = call.methodDescriptor.fullMethodName
        requestDetail["requested_at"] = begin.toString()
        requestDetail["metadata"] = headers.keys().associateWith {
            headers[Metadata.Key.of(it, Metadata.ASCII_STRING_MARSHALLER)]
        }

        return try {
            next.startCall(
                object : ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {

                    var end: LocalDateTime? = null

                    @Suppress("kotlin:S108")
                    override fun sendMessage(message: RespT) {
                        end = LocalDateTime.now()
                        requestDetail["metadata"] = headers.keys().associateWith {
                            headers[Metadata.Key.of(it, Metadata.ASCII_STRING_MARSHALLER)]
                        }
                        if (message is GeneratedMessageV3) {
                            requestDetail["response"] = JsonUtils.decode(
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
                        requestDetail["hint"] = hint

                        Logger.info(mapOf("message" to "access log", "request" to requestDetail))
                        super.sendMessage(message)
                    }
                },
                headers,
            )
        } catch (e: Exception) {
            Logger.error(payload = mapOf("message" to "access log", "request" to requestDetail))
            throw Status.INTERNAL.withCause(e).asException()
        }
    }
}
