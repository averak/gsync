package net.averak.gsync.domain.model

import java.util.*

/**
 * 運営スタッフ
 */
data class Operator(
    val id: UUID,
    val email: String,
    val authorities: List<OperatorAuthority>,
)

data class OperatorAuthority(
    val tenantID: UUID,
    val isAdmin: Boolean,
)
