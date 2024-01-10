package net.averak.gsync.adapter.handler.player_api

import io.grpc.stub.StreamObserver
import net.averak.gsync.adapter.pbconv.PlayerStorageConverter
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageClearV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageGrpc
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.usecase.PlayerStorageUsecase
import org.lognet.springboot.grpc.GRpcService
import java.time.LocalDateTime
import java.util.*

@GRpcService
class PlayerStorageHandler(
    private val playerStorageUsecase: PlayerStorageUsecase,
) : PlayerStorageGrpc.PlayerStorageImplBase() {

    override fun searchV1(request: PlayerStorageSearchV1.Request, responseObserver: StreamObserver<PlayerStorageSearchV1.Response>) {
        val gctx = GameContext(
            masterVersion = UUID.randomUUID(),
            idempotencyKey = UUID.randomUUID(),
            dateline = Dateline.DEFAULT,
            currentTime = LocalDateTime.now(),
        )
        val result = playerStorageUsecase.search(
            gctx,
            UUID.randomUUID(),
            UUID.randomUUID(),
            PlayerStorageConverter.fromPb(request.criteria),
        )

        responseObserver.onNext(PlayerStorageConverter.toPb(result))
        responseObserver.onCompleted()
    }

    override fun setV1(request: PlayerStorageSetV1.Request, responseObserver: StreamObserver<PlayerStorageSetV1.Response>) {
        val gctx = GameContext(
            masterVersion = UUID.randomUUID(),
            idempotencyKey = UUID.randomUUID(),
            dateline = Dateline.DEFAULT,
            currentTime = LocalDateTime.now(),
        )
        val result = playerStorageUsecase.set(
            gctx,
            UUID.randomUUID(),
            UUID.randomUUID(),
            PlayerStorageConverter.fromPb(request.entry),
            UUID.fromString(request.previousRevision),
        )

        responseObserver.onNext(PlayerStorageConverter.toPb(result))
        responseObserver.onCompleted()
    }

    override fun clearV1(request: PlayerStorageClearV1.Request, responseObserver: StreamObserver<PlayerStorageClearV1.Response>) {
        val gctx = GameContext(
            masterVersion = UUID.randomUUID(),
            idempotencyKey = UUID.randomUUID(),
            dateline = Dateline.DEFAULT,
            currentTime = LocalDateTime.now(),
        )
        val result = playerStorageUsecase.clear(
            gctx,
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.fromString(request.previousRevision),
            PlayerStorageConverter.fromPb(request.criteria),
        )

        responseObserver.onNext(
            PlayerStorageClearV1.Response.newBuilder()
                .setNextRevision(result.toString())
                .build(),
        )
        super.clearV1(request, responseObserver)
    }
}
