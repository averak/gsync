package net.averak.gsync.domain.model

import java.util.*

/**
 * テナント (＝ゲームタイトル)
 */
data class Tenant(
    val id: UUID,
    val name: String,
)
