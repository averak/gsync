package net.averak.gsync.adapter.handler.player_api.pbconv

import net.averak.gsync.domain.model.Friend
import net.averak.gsync.domain.model.FriendRequest

class FriendConverter {

    companion object {

        @JvmStatic
        fun toPb(friend: Friend): net.averak.gsync.schema.protobuf.resource.friend.Friend {
            return net.averak.gsync.schema.protobuf.resource.friend.Friend.newBuilder()
                .setPlayerId(friend.friendPlayerID.toString())
                .setBecomeFriendAt(LocalDateTimeConverter.toPb(friend.becomeFriendAt))
                .setProfile(PlayerProfileConverter.toPb(friend.profile)).build()
        }

        @JvmStatic
        fun toPb(friendRequest: FriendRequest): net.averak.gsync.schema.protobuf.resource.friend.FriendRequest {
            return net.averak.gsync.schema.protobuf.resource.friend.FriendRequest.newBuilder()
                .setSenderPlayerId(friendRequest.playerID.toString())
                .setReceiverPlayerId(friendRequest.receiverPlayerID.toString())
                .setSentAt(LocalDateTimeConverter.toPb(friendRequest.sentAt)).build()
        }
    }
}
