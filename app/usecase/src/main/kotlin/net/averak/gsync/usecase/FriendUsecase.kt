package net.averak.gsync.usecase

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Friend
import net.averak.gsync.domain.repository.IFriendRepository
import net.averak.gsync.usecase.transaction.ITransaction
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FriendUsecase(
    private val transaction: ITransaction,
    private val friendRepository: IFriendRepository,
) {

    fun list(gctx: GameContext, playerID: UUID): List<Friend> {
        return transaction.roTx {
            return@roTx friendRepository.getFriends(gctx, playerID)
        }
    }
}
