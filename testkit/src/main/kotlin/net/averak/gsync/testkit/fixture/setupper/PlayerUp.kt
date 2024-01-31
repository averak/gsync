package net.averak.gsync.testkit.fixture.setupper

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.repository.IFriendRepository
import net.averak.gsync.domain.repository.IPlayerRepository
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.fixture.builder.player.PlayerData
import org.springframework.stereotype.Component

@Component
class PlayerUp(
    private val playerRepository: IPlayerRepository,
    private val playerStorageRepository: IPlayerStorageRepository,
    private val friendRepository: IFriendRepository,
) {

    fun setup(vararg data: PlayerData) {
        setup(Faker.fake(GameContext::class.java), *data)
    }

    fun setup(gctx: GameContext, vararg data: PlayerData) {
        data.forEach { player ->
            playerRepository.save(gctx, player.player)
            if (player.playerStorage != null) {
                playerStorageRepository.save(gctx, player.playerStorage)
            }
            player.friendships.forEach { friendship ->
                friendRepository.saveFriendship(gctx, friendship)
            }
            player.friendRequests.forEach { friendRequest ->
                friendRepository.saveFriendRequest(gctx, friendRequest)
            }
        }
    }
}
