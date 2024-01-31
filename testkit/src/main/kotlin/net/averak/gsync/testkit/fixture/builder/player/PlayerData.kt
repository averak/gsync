package net.averak.gsync.testkit.fixture.builder.player

import net.averak.gsync.domain.model.FriendRequest
import net.averak.gsync.domain.model.Friendship
import net.averak.gsync.domain.model.Player
import net.averak.gsync.domain.model.PlayerStorage

data class PlayerData(
    val player: Player,
    val playerStorage: PlayerStorage? = null,
    val friendships: List<Friendship> = emptyList(),
    val friendRequests: List<FriendRequest> = emptyList(),
)
