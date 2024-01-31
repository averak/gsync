package net.averak.gsync.adapter.handler.player_api.pbconv

import net.averak.gsync.domain.model.Friend

class FriendConverter {

    companion object {

        @JvmStatic
        fun toPb(friend: Friend): net.averak.gsync.schema.protobuf.resource.friend.Friend {
            return net.averak.gsync.schema.protobuf.resource.friend.Friend.newBuilder()
                .setPlayerId(friend.friendPlayerID.toString())
                .setBecomeFriendAt(LocalDateTimeConverter.toPb(friend.becomeFriendAt))
                .setProfile(PlayerProfileConverter.toPb(friend.profile)).build()
        }
    }
}
