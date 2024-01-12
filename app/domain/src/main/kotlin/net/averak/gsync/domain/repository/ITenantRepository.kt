package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Tenant
import java.util.*

interface ITenantRepository {

    fun findByIDs(gctx: GameContext, ids: List<UUID>): List<Tenant>

    fun save(gctx: GameContext, tenant: Tenant)
}
