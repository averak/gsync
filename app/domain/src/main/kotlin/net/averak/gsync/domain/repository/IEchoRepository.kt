package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Echo
import java.util.*

interface IEchoRepository {

    fun save(gctx: GameContext, echo: Echo)

    fun findByID(gctx: GameContext, id: UUID): Echo?
}
