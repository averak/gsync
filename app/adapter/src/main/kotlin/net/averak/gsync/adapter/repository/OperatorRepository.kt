package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.extend.OperatorILDto
import net.averak.gsync.adapter.dao.mapper.extend.OperatorMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.GameOperationAuthority
import net.averak.gsync.domain.model.Operator
import net.averak.gsync.domain.repository.IOperatorRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class OperatorRepository(
    private val operatorMapper: OperatorMapper,
) : IOperatorRepository {

    override fun findByID(gctx: GameContext, id: UUID): Operator? {
        return operatorMapper.selectByOperatorId(id.toString())?.let { convertDtoToModel(it) }
    }

    override fun findByGameID(gctx: GameContext, gameID: UUID): List<Operator> {
        return operatorMapper.selectByGameId(gameID.toString()).map {
            convertDtoToModel(it)
        }
    }

    companion object {

        fun convertDtoToModel(dto: OperatorILDto): Operator {
            return Operator(
                id = UUID.fromString(dto.operatorId),
                email = dto.email,
                authorities = dto.rGameOperators.map { rGameOperator ->
                    GameOperationAuthority(
                        gameID = UUID.fromString(rGameOperator.gameId),
                        isAdmin = rGameOperator.isAdmin,
                    )
                },
            )
        }
    }
}
