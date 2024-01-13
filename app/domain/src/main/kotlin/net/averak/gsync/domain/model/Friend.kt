package net.averak.gsync.domain.model

import java.time.LocalDateTime
import java.util.*

data class Friend(
    val playerID: UUID,
    val friendPlayerID: UUID,
    val becomeFriendAt: LocalDateTime,
    val profile: PlayerProfile,
)

data class FriendRequest(
    val playerID: UUID,
    val receiverPlayerID: UUID,
    val sentAt: LocalDateTime,
) {

    fun approved(now: LocalDateTime): Friendship {
        return Friendship(playerID, receiverPlayerID, now)
    }
}

/**
 * 相互にフレンドである関係を表す。
 * follower-followee のような一方向の関係は許されず、「Aから見てBはフレンド」ならば「Bから見てAはフレンド」が必ず成り立つ。
 */
data class Friendship(
    val playerIDs: Pair<UUID, UUID>,
    val becomeFriendAt: LocalDateTime,
) {

    constructor(player1ID: UUID, player2ID: UUID, becomeFriendAt: LocalDateTime) : this(
        playerIDs = Pair(player1ID, player2ID),
        becomeFriendAt,
    )

    init {
        require(playerIDs.first != playerIDs.second) {
            "playerIDs must be different."
        }
    }
}

enum class FriendStatus {
    NONE,
    FRIEND,
    SENT_REQUEST,
    RECEIVED_REQUEST,
}

data class FriendSetting(
    val maxFriendCount: Int,
    /**
     * 未承認のフレンドリクエストの最大数
     */
    val maxFriendRequestCount: Int,
)
