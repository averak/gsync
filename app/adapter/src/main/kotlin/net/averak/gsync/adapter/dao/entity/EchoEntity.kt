package net.averak.gsync.adapter.dao.entity

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.LocalDateTime
import java.util.*

@Table(name = "echo")
data class EchoEntity(
    @Id
    @JdbcTypeCode(Types.CHAR)
    var id: UUID,

    @Column(length = 255)
    var message: String,

    @Column
    var timestamp: LocalDateTime,
)
