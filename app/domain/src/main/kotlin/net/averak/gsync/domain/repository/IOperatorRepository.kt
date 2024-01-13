package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Operator
import java.util.*

interface IOperatorRepository {

    fun findByID(gctx: GameContext, id: UUID): Operator?

    fun findByTenantID(gctx: GameContext, tenantID: UUID): List<Operator>
}
