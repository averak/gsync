package net.averak.gsync.domain.model

import java.util.*

/**
 * 運営スタッフ
 */
data class Operator(
    val id: UUID,
    val email: String,
    val authorities: List<GameOperationAuthority>,
)

data class GameOperationAuthority(
    val gameID: UUID,
    val isAdmin: Boolean,
)
