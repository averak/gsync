package net.averak.gsync.domain.model

import java.time.LocalDateTime
import java.util.*

data class Player(
    val id: UUID,
    val friendID: UUID,
    val isBanned: Boolean,
    val profile: PlayerProfile,
    var login: PlayerLogin?,
)

data class PlayerProfile(
    val nickname: String,
    val iconID: String,
) {

    init {
        require(nickname.isNotBlank() && nickname.length in 1..10) {
            "nickname must be 1 to 10 characters long, but was `$nickname`."
        }
    }
}

data class PlayerLogin(
    val totalLoginDays: Int,
    val lastLoggedInAt: LocalDateTime,
) {

    init {
        require(totalLoginDays >= 0) {
            "totalLoginDays must be greater than or equal to 0, but was `$totalLoginDays`."
        }
    }
}
