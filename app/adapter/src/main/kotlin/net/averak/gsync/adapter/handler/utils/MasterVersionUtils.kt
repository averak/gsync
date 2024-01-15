package net.averak.gsync.adapter.handler.utils

import net.averak.gsync.adapter.dao.dto.base.MasterVersionExample
import net.averak.gsync.adapter.dao.mapper.base.MasterVersionBaseMapper
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import org.springframework.stereotype.Component
import java.util.*

@Component
class MasterVersionUtils(
    private val masterVersionMapper: MasterVersionBaseMapper,
) {

    @Throws(GsyncException::class)
    fun getEnabledMasterVersion(spoofingMasterVersion: UUID?): UUID {
        return if (spoofingMasterVersion == null) {
            val dtos = masterVersionMapper.selectByExample(
                MasterVersionExample().apply {
                    createCriteria().andIsEnabledEqualTo(true)
                },
            )
            if (dtos.isEmpty()) {
                throw GsyncException(ErrorCode.ENABLED_MASTER_VERSION_DEFINITION_IS_NOT_FOUND)
            }
            if (dtos.size > 1) {
                throw GsyncException(ErrorCode.MULTIPLE_ENABLED_MASTER_VERSIONS_ARE_DEFINED)
            }
            UUID.fromString(dtos[0].version)
        } else {
            spoofingMasterVersion
        }
    }
}
