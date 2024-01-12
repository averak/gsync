package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.extend.OperatorILDto
import net.averak.gsync.adapter.dao.mapper.extend.OperatorMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Operator
import net.averak.gsync.domain.model.OperatorAuthority
import net.averak.gsync.domain.repository.IOperatorRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class OperatorRepository(
    private val operatorMapper: OperatorMapper,
) : IOperatorRepository {

    override fun findByID(gctx: GameContext, id: UUID): Operator? {
        return operatorMapper.selectByOperatorId(id.toString())?.toModel()
    }

    override fun findByTenantID(gctx: GameContext, tenantID: UUID): List<Operator> {
        return operatorMapper.selectByTenantId(tenantID.toString()).map {
            it.toModel()
        }
    }

    private fun OperatorILDto.toModel(): Operator {
        return Operator(
            id = UUID.fromString(operatorId),
            email = email,
            authorities = rTenantOperators.map { rTenantOperator ->
                OperatorAuthority(
                    tenantID = UUID.fromString(rTenantOperator.tenantId),
                    isAdmin = rTenantOperator.isAdmin,
                )
            },
        )
    }
}
