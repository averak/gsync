package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.pbconv.EchoConverter
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.schema.protobuf.player_api.EchoGrpc
import net.averak.gsync.usecase.EchoUsecase
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class EchoHandler(
    private val echoUsecase: EchoUsecase,
) : EchoGrpc.EchoImplBase() {

    override fun echoV1(request: EchoEchoV1.Request, responseObserver: io.grpc.stub.StreamObserver<EchoEchoV1.Response>) {
        val gctx = GameContext(
            masterVersion = UUID.randomUUID(),
            idempotencyKey = UUID.randomUUID(),
            dateline = Dateline.DEFAULT,
            currentTime = LocalDateTime.now(),
        )
        val result = echoUsecase.echo(gctx, request.message)

        responseObserver.onNext(EchoConverter.toPb(result))
        responseObserver.onCompleted()
    }
}
