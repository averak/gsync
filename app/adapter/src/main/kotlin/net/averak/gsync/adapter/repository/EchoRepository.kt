package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.entity.base.EchoEntity
import net.averak.gsync.adapter.dao.mapper.extend.EchoMapper
import net.averak.gsync.domain.model.Echo
import net.averak.gsync.domain.repository.IEchoRepository
import org.springframework.stereotype.Repository
import java.time.ZoneOffset
import java.util.*

@Repository
open class EchoRepository(
    private val echoMapper: EchoMapper,
) : IEchoRepository {

    override fun save(echo: Echo) {
        val entity = EchoEntity(
            echo.id.toString(),
            echo.message,
            Date.from(echo.timestamp.toInstant(ZoneOffset.UTC)),
        )
        echoMapper.insert(entity)
    }
}
