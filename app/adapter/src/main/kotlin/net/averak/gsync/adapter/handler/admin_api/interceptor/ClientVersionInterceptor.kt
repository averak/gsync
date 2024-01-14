package net.averak.gsync.adapter.handler.admin_api.interceptor

import com.google.common.annotations.VisibleForTesting
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionExample
import net.averak.gsync.adapter.dao.mapper.base.RequiredClientVersionBaseMapper
import net.averak.gsync.adapter.handler.admin_api.HttpRequestScope
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.domain.model.Platform
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import java.util.*

@Component
open class ClientVersionInterceptor(
    private val requestScope: HttpRequestScope,
    private val requiredClientVersionBaseMapper: RequiredClientVersionBaseMapper,
) : IRequestInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val clientVersion = requestScope.getClientVersion()
        val platform = requestScope.getPlatform()
        val gctx = requestScope.getGameContext()
        if (!verifyClientVersion(gctx.masterVersion, clientVersion, platform)) {
            throw GsyncException(ErrorCode.CLIENT_VERSION_IS_NOT_SUPPORTED)
        }
        return true
    }

    @Throws(GsyncException::class)
    @VisibleForTesting
    fun verifyClientVersion(masterVersion: UUID, clientVersion: String?, platform: Platform?): Boolean {
        // REST API の場合は全ての API がクライアントバージョン、プラットフォームを要求するわけではない
        // しかし API によっては必須の場合もあるので、そのチェックは個別で行う
        if (clientVersion == null && platform == null) {
            return true
        }

        // クライアントバージョン、プラットフォームが片方でも指定された場合は、要求されるバージョンを満たしていることを判定する (両方未指定の場合のみ判定処理をスキップして良い)
        if (clientVersion == null) {
            throw GsyncException(ErrorCode.CLIENT_VERSION_MUST_BE_SPECIFIED)
        }
        if (platform == null) {
            throw GsyncException(ErrorCode.PLATFORM_MUST_BE_SPECIFIED)
        }
        val dtos = requiredClientVersionBaseMapper.selectByExample(
            RequiredClientVersionExample().apply {
                createCriteria().andMasterVersionEqualTo(masterVersion.toString()).andPlatformEqualTo(platform.id.toLong())
            },
        )
        if (dtos.isEmpty()) {
            throw GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
        }

        return compareSemver(clientVersion, dtos[0].clientVersion)
    }

    /**
     * ver1 >= ver2 を判定する
     */
    private fun compareSemver(ver1: String, ver2: String): Boolean {
        val major1 = ver1.trim('v').split(".")[0].toInt()
        val minor1 = ver1.trim('v').split(".")[1].toInt()
        val patch1 = ver1.trim('v').split(".")[2].toInt()
        val major2 = ver2.trim('v').split(".")[0].toInt()
        val minor2 = ver2.trim('v').split(".")[1].toInt()
        val patch2 = ver2.trim('v').split(".")[2].toInt()
        return major1 > major2 || (major1 == major2 && minor1 > minor2) || (major1 == major2 && minor1 == minor2 && patch1 >= patch2)
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        return
    }

    override fun getPriority(): InterceptorPriority {
        return InterceptorPriority.MEDIUM
    }
}
