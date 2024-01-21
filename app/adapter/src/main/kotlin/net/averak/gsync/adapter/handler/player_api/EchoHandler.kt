package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.handler.player_api.pbconv.EchoConverter
import net.averak.gsync.infrastructure.grpc.player_api.Request
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.schema.protobuf.player_api.IEchoHandler
import net.averak.gsync.usecase.EchoUsecase
import org.springframework.stereotype.Service

@Service
class EchoHandler(
    private val echoUsecase: EchoUsecase,
) : IEchoHandler {

    override fun echoV1(request: Request<EchoEchoV1.Request>): EchoEchoV1.Response {
        val result = echoUsecase.echo(request.gctx, request.message.message)
        return EchoConverter.toPb(result)
    }
}
