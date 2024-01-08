package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.entity.base.EchoEntity
import net.averak.gsync.adapter.dao.mapper.extend.EchoMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Echo
import net.averak.gsync.domain.repository.IEchoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class EchoRepository(
    private val echoMapper: EchoMapper,
) : IEchoRepository {

    override fun save(gctx: GameContext, echo: Echo) {
        val entity = echoMapper.selectByPrimaryKey(echo.id.toString())
        if (entity == null) {
            echoMapper.insert(
                EchoEntity(
                    echo.id.toString(),
                    echo.message,
                    echo.timestamp,
                    gctx.currentTime,
                    gctx.currentTime,
                ),
            )
        } else {
            entity.message = echo.message
            entity.timestamp = echo.timestamp
            entity.updatedAt = gctx.currentTime
            echoMapper.updateByPrimaryKey(entity)
        }
    }

    override fun findByID(gctx: GameContext, id: UUID): Echo? {
        return echoMapper.selectByPrimaryKey(id.toString())?.let {
            Echo(
                UUID.fromString(it.echoId),
                it.message,
                it.timestamp,
            )
        }
    }
}
