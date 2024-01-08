package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.EchoDao
import net.averak.gsync.domain.model.Echo
import net.averak.gsync.domain.repository.IEchoRepository
import org.springframework.stereotype.Repository

@Repository
open class EchoRepository(
    private val echoDao: EchoDao,
) : IEchoRepository {

    override fun save(echo: Echo) {
        echoDao.insert(echo.id, echo.message, echo.timestamp)
    }
}
