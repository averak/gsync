package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.handler.player_api.pbconv.PlayerStorageConverter
import net.averak.gsync.infrastructure.grpc.player_api.Request
import net.averak.gsync.schema.protobuf.player_api.IPlayerStorageHandler
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.usecase.PlayerStorageUsecase
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerStorageHandler(
    private val playerStorageUsecase: PlayerStorageUsecase,
) : IPlayerStorageHandler {

    override fun searchV1(request: Request<PlayerStorageSearchV1.Request>): PlayerStorageSearchV1.Response {
        val result = playerStorageUsecase.search(
            request.gctx,
            request.mustPrincipal().playerID,
            request.mustPrincipal().gameID,
            PlayerStorageConverter.fromPb(request.message.criteria),
        )
        return PlayerStorageConverter.toPb(result)
    }

    override fun setV1(request: Request<PlayerStorageSetV1.Request>): PlayerStorageSetV1.Response {
        val result = playerStorageUsecase.set(
            request.gctx,
            request.mustPrincipal().playerID,
            request.mustPrincipal().gameID,
            PlayerStorageConverter.fromPb(request.message.entry),
            UUID.fromString(request.message.previousRevision),
        )
        return PlayerStorageConverter.toPb(result)
    }

    override fun clearV1(request: Request<PlayerStorageClearV1.Request>): PlayerStorageClearV1.Response {
        val result = playerStorageUsecase.clear(
            request.gctx,
            request.mustPrincipal().playerID,
            request.mustPrincipal().gameID,
            UUID.fromString(request.message.previousRevision),
            PlayerStorageConverter.fromPb(request.message.criteria),
        )
        return PlayerStorageClearV1.Response.newBuilder().setNextRevision(result.toString()).build()
    }
}
