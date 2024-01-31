package net.averak.gsync.adapter.handler.player_api.pbconv

import net.averak.gsync.domain.model.PlayerProfile

class PlayerProfileConverter {

    companion object {

        @JvmStatic
        fun toPb(profile: PlayerProfile): net.averak.gsync.schema.protobuf.resource.player.PlayerProfile {
            return net.averak.gsync.schema.protobuf.resource.player.PlayerProfile.newBuilder()
                .setNickname(profile.nickname)
                .setIconId(profile.iconID)
                .build()
        }
    }
}
