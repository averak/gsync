package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.TenantDto
import net.averak.gsync.adapter.dao.dto.base.TenantExample
import net.averak.gsync.adapter.dao.mapper.base.TenantBaseMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Tenant
import net.averak.gsync.domain.repository.ITenantRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class TenantRepository(
    private val tenantMapper: TenantBaseMapper,
) : ITenantRepository {

    override fun findByIDs(gctx: GameContext, ids: List<UUID>): List<Tenant> {
        if (ids.isEmpty()) {
            return emptyList()
        }

        return tenantMapper.selectByExample(
            TenantExample().apply {
                createCriteria().andTenantIdIn(ids.map { it.toString() })
            },
        ).map {
            Tenant(
                id = UUID.fromString(it.tenantId),
                name = it.name,
            )
        }
    }

    override fun save(gctx: GameContext, tenant: Tenant) {
        val dto = tenantMapper.selectByPrimaryKey(tenant.id.toString())
        if (dto == null) {
            tenantMapper.insert(
                TenantDto(
                    tenant.id.toString(),
                    tenant.name,
                    gctx.currentTime,
                    gctx.currentTime,
                ),
            )
        } else {
            tenantMapper.updateByPrimaryKey(
                TenantDto(
                    tenant.id.toString(),
                    tenant.name,
                    dto.createdAt,
                    gctx.currentTime,
                ),
            )
        }
    }
}
