package net.averak.gsync.adapter.handler.player_api

import io.grpc.stub.StreamObserver
import net.averak.gsync.adapter.handler.player_api.pbconv.PlayerStorageConverter
import net.averak.gsync.adapter.handler.player_api.scope.RequestScope
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageGrpc
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.usecase.PlayerStorageUsecase
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerStorageHandler(
    private val requestScope: RequestScope,
    private val playerStorageUsecase: PlayerStorageUsecase,
) : PlayerStorageGrpc.PlayerStorageImplBase() {

    override fun searchV1(request: PlayerStorageSearchV1.Request, responseObserver: StreamObserver<PlayerStorageSearchV1.Response>) {
        val result = playerStorageUsecase.search(
            requestScope.gctx,
            requestScope.playerID,
            requestScope.gameID,
            PlayerStorageConverter.fromPb(request.criteria),
        )

        responseObserver.onNext(PlayerStorageConverter.toPb(result))
        responseObserver.onCompleted()
    }

    override fun setV1(request: PlayerStorageSetV1.Request, responseObserver: StreamObserver<PlayerStorageSetV1.Response>) {
        val result = playerStorageUsecase.set(
            requestScope.gctx,
            requestScope.playerID,
            requestScope.gameID,
            PlayerStorageConverter.fromPb(request.entry),
            UUID.fromString(request.previousRevision),
        )

        responseObserver.onNext(PlayerStorageConverter.toPb(result))
        responseObserver.onCompleted()
    }

    override fun clearV1(request: PlayerStorageClearV1.Request, responseObserver: StreamObserver<PlayerStorageClearV1.Response>) {
        val result = playerStorageUsecase.clear(
            requestScope.gctx,
            requestScope.playerID,
            requestScope.gameID,
            UUID.fromString(request.previousRevision),
            PlayerStorageConverter.fromPb(request.criteria),
        )

        responseObserver.onNext(
            PlayerStorageClearV1.Response.newBuilder().setNextRevision(result.toString()).build(),
        )
        super.clearV1(request, responseObserver)
    }
}
