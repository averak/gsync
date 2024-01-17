package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.handler.player_api.mdval.RequestScope
import net.averak.gsync.adapter.handler.player_api.pbconv.EchoConverter
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.schema.protobuf.player_api.EchoGrpc
import net.averak.gsync.usecase.EchoUsecase
import org.springframework.stereotype.Service

@Service
class EchoHandler(
    private val requestScope: RequestScope,
    private val echoUsecase: EchoUsecase,
) : EchoGrpc.EchoImplBase() {

    override fun echoV1(request: EchoEchoV1.Request, responseObserver: io.grpc.stub.StreamObserver<EchoEchoV1.Response>) {
        val result = echoUsecase.echo(requestScope.gctx, request.message)

        responseObserver.onNext(EchoConverter.toPb(result))
        responseObserver.onCompleted()
    }
}
