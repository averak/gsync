package net.averak.gsync.infrastructure.mybatis.type_handler

import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedTypes
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime

@MappedTypes(LocalDateTime::class)
class LocalDateTimeTypeHandler : BaseTypeHandler<LocalDateTime>() {

    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: LocalDateTime, jdbcType: JdbcType) {
        ps.setTimestamp(i, Timestamp.valueOf(parameter))
    }

    override fun getNullableResult(rs: ResultSet, columnName: String): LocalDateTime? {
        val timestamp = rs.getTimestamp(columnName)
        return timestamp?.toLocalDateTime()
    }

    override fun getNullableResult(rs: ResultSet, columnIndex: Int): LocalDateTime? {
        val timestamp = rs.getTimestamp(columnIndex)
        return timestamp?.toLocalDateTime()
    }

    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): LocalDateTime? {
        val timestamp = cs.getTimestamp(columnIndex)
        return timestamp?.toLocalDateTime()
    }
}
