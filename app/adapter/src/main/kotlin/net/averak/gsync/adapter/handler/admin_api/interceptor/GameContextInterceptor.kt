package net.averak.gsync.adapter.handler.admin_api.interceptor

import com.google.common.annotations.VisibleForTesting
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.gsync.adapter.dao.dto.base.MasterVersionExample
import net.averak.gsync.adapter.dao.mapper.base.MasterVersionBaseMapper
import net.averak.gsync.adapter.handler.admin_api.HttpRequestScope
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import java.util.*

@Component
open class GameContextInterceptor(
    private val requestScope: HttpRequestScope,
    private val masterVersionMapper: MasterVersionBaseMapper,
) : IRequestInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val gctx = GameContext(
            getEnabledMasterVersion(requestScope.getSpoofingMasterVersion()),
            // クライアントが Idempotency-Key を必ず設定してくるとは限らないので、未設定の場合はサーバ側でユニークキーを発行し、毎回異なるリクエストとして扱う
            requestScope.getIdempotencyKey() ?: UUID.randomUUID(),
            Dateline.DEFAULT,
            requestScope.getSpoofingCurrentTime() ?: LocalDateTime.now(),
        )
        requestScope.setGameContext(gctx)
        return true
    }

    @Throws(GsyncException::class)
    @VisibleForTesting
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

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        return
    }

    override fun getPriority(): InterceptorPriority {
        return InterceptorPriority.HIGH
    }
}
