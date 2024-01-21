package net.averak.gsync.infrastructure.grpc.player_api

import com.google.protobuf.AbstractMessage
import io.grpc.stub.StreamObserver
import net.averak.gsync.infrastructure.grpc.player_api.metadata.RequestScope
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
        } catch (ex: Exception) {
            responseObserver.onError(ex)
        } finally {
            responseObserver.onCompleted()
        }
    }
}
