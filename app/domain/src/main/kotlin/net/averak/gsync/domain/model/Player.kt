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
)

data class PlayerLogin(
    val totalLoginDays: Int,
    val lastLoggedInAt: LocalDateTime,
)
