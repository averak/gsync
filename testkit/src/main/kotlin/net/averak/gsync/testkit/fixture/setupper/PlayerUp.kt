package net.averak.gsync.testkit.fixture.setupper

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.repository.IFriendRepository
import net.averak.gsync.domain.repository.IPlayerRepository
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.testkit.fixture.builder.player.Registry
import org.springframework.stereotype.Component

@Component
class PlayerUp(
    private val playerRepository: IPlayerRepository,
    private val playerStorageRepository: IPlayerStorageRepository,
    private val friendRepository: IFriendRepository,
) {

    fun setup(gctx: GameContext, vararg registry: Registry) {
        registry.forEach { data ->
            if (data.player != null) {
                playerRepository.save(gctx, data.player)
            }
            if (data.playerStorage != null) {
                playerStorageRepository.save(gctx, data.playerStorage)
            }
            data.friendships.forEach { friendship ->
                friendRepository.saveFriendship(gctx, friendship)
            }
            data.friendRequests.forEach { friendRequest ->
                friendRepository.saveFriendRequest(gctx, friendRequest)
            }
        }
    }
}
