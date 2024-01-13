package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.EchoDto
import net.averak.gsync.adapter.dao.mapper.base.EchoBaseMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Echo
import net.averak.gsync.domain.repository.IEchoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class EchoRepository(
    private val echoMapper: EchoBaseMapper,
) : IEchoRepository {

    override fun save(gctx: GameContext, echo: Echo) {
        val dto = EchoDto(
            echo.id.toString(),
            echo.message,
            echo.timestamp,
            gctx.currentTime,
            gctx.currentTime,
        )
        echoMapper.syncOriginal(dto)
        echoMapper.insertOrUpdate(dto)
    }

    override fun findByID(gctx: GameContext, id: UUID): Echo? {
        return echoMapper.selectByPrimaryKey(id.toString())?.let {
            Echo(
                id = UUID.fromString(it.echoId),
                message = it.message,
                timestamp = it.timestamp,
            )
        }
    }
}
