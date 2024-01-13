package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Game
import java.util.*

interface IGameRepository {

    fun findByIDs(gctx: GameContext, ids: List<UUID>): List<Game>

    fun save(gctx: GameContext, game: Game)
}
