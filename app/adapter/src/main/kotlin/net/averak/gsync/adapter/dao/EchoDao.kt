package net.averak.gsync.adapter.dao

import com.google.cloud.spring.data.spanner.repository.SpannerRepository
import com.google.cloud.spring.data.spanner.repository.query.Query
import net.averak.gsync.adapter.dao.entity.EchoEntity
import java.time.LocalDateTime
import java.util.*

interface EchoDao : SpannerRepository<EchoEntity, String> {

    @Query("INSERT INTO echo (id, message, timestamp) VALUES (@id, @message, @timestamp)", dmlStatement = true)
    fun insert(id: UUID, message: String, timestamp: LocalDateTime)
}
