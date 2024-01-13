package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Player
import java.util.*

interface IPlayerRepository {

    fun get(gctx: GameContext, id: UUID): Player?

    fun save(gctx: GameContext, player: Player)
}
