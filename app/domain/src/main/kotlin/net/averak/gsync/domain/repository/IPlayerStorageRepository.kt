package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import java.util.*

interface IPlayerStorageRepository {

    fun get(gctx: GameContext, playerID: UUID, tenantID: UUID, criteria: PlayerStorageCriteria): PlayerStorage

    fun save(gctx: GameContext, playerStorage: PlayerStorage)
}

data class PlayerStorageCriteria(
    val exactMatch: List<String>,
    val forwardMatch: List<String>,
) {

    fun isEmpty(): Boolean {
        return exactMatch.isEmpty() && forwardMatch.isEmpty()
    }
}
