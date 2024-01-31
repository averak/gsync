package net.averak.gsync.usecase

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Friend
import net.averak.gsync.domain.model.FriendRequest
import net.averak.gsync.domain.repository.IFriendRepository
import net.averak.gsync.usecase.transaction.ITransaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class FriendUsecase(
    private val transaction: ITransaction,
    private val friendRepository: IFriendRepository,
) {

    fun list(gctx: GameContext, playerID: UUID): ListResult {
        return transaction.roTx {
            val friends = friendRepository.getFriends(gctx, playerID)
            val sentFriendRequests = friendRepository.getSentFriendRequests(gctx, playerID)
            val receivedFriendRequests = friendRepository.getReceivedFriendRequests(gctx, playerID)
            return@roTx ListResult(
                friends = friends,
                sentFriendRequests = sentFriendRequests,
                receivedFriendRequests = receivedFriendRequests,
            )
        }
    }

    fun sendRequest(gctx: GameContext, playerID: UUID, targetPlayerID: UUID) {
        val setting = transaction.roTx {
            friendRepository.getSetting(gctx)
        }

        transaction.rwTx {
            val sentFriendRequests = friendRepository.getSentFriendRequests(gctx, playerID)
            check(sentFriendRequests.size < setting.maxFriendRequestCount) {
                "player sent friend requests is over max count."
            }

            val friendRequest = FriendRequest(
                playerID,
                targetPlayerID,
                gctx.currentTime,
            )
            friendRepository.saveFriendRequest(gctx, friendRequest)
        }
    }

    data class ListResult(
        val friends: List<Friend>,
        val sentFriendRequests: List<FriendRequest>,
        val receivedFriendRequests: List<FriendRequest>,
    )
}
