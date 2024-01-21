package net.averak.gsync.infrastructure.grpc.player_api

import com.google.protobuf.AbstractMessage
import io.grpc.Status
import io.grpc.stub.StreamObserver
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.infrastructure.grpc.player_api.metadata.RequestScope
import net.averak.gsync.infrastructure.json.JsonUtils
import org.springframework.stereotype.Component

@Component
class HandlerWrapper(
    private val requestScope: RequestScope,
) {

    fun <REQ : AbstractMessage, RES : AbstractMessage> invoke(
        method: (Request<REQ>) -> RES,
        message: REQ,
        responseObserver: StreamObserver<RES>,
    ) {
        val principal = try {
            Principal(
                requestScope.playerID,
                requestScope.gameID,
            )
        } catch (ex: NullPointerException) {
            null
        }
        val request = Request(
            requestScope.gctx,
            principal,
            message,
        )

        try {
            val response = method(request)
            responseObserver.onNext(response)
        } catch (ex: GsyncException) {
            responseObserver.onError(
                Status.ABORTED.withCause(ex).withDescription(JsonUtils.encode(ErrorResponse(ex))).asRuntimeException(),
            )
        } catch (ex: Exception) {
            responseObserver.onError(
                Status.INTERNAL.withCause(ex).asRuntimeException(),
            )
        } finally {
            responseObserver.onCompleted()
        }
    }

    data class ErrorResponse(
        val code: String,
        val message: String,
    ) {

        constructor(ex: GsyncException) : this(ex.errorCode.name, ex.errorCode.summary)
    }
}
