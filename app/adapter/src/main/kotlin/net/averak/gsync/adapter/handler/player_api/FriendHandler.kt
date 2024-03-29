package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.adapter.handler.player_api.pbconv.FriendConverter
import net.averak.gsync.infrastructure.grpc.player_api.Request
import net.averak.gsync.schema.protobuf.player_api.FriendListV1
import net.averak.gsync.schema.protobuf.player_api.FriendSendRequestV1
import net.averak.gsync.schema.protobuf.player_api.IFriendHandler
import net.averak.gsync.usecase.FriendUsecase
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendHandler(
    private val friendUsecase: FriendUsecase,
) : IFriendHandler {

    override fun listV1(request: Request<FriendListV1.Request>): FriendListV1.Response {
        val result = friendUsecase.list(request.gctx, request.mustPrincipal().playerID)
        return FriendListV1.Response.newBuilder()
            .addAllFriends(result.friends.map { FriendConverter.toPb(it) })
            .addAllSentFriendRequests(result.sentFriendRequests.map { FriendConverter.toPb(it) })
            .addAllReceivedFriendRequests(result.receivedFriendRequests.map { FriendConverter.toPb(it) })
            .build()
    }

    override fun sendRequestV1(request: Request<FriendSendRequestV1.Request>): FriendSendRequestV1.Response {
        friendUsecase.sendRequest(request.gctx, request.mustPrincipal().playerID, UUID.fromString(request.message.playerId))
        return FriendSendRequestV1.Response.newBuilder().build()
    }
}
