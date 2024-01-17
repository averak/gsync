package net.averak.gsync.adapter.handler.admin_api.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.gsync.adapter.handler.admin_api.RequestScope
import net.averak.gsync.adapter.handler.utils.MasterVersionUtils
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import java.util.*

@Component
open class GameContextInterceptor(
    private val requestScope: RequestScope,
    private val masterVersionUtils: MasterVersionUtils,
) : IRequestInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val gctx = GameContext(
            masterVersionUtils.getEnabledMasterVersion(requestScope.getSpoofingMasterVersion()),
            // クライアントが Idempotency-Key を必ず設定してくるとは限らないので、未設定の場合はサーバ側でユニークキーを発行し、毎回異なるリクエストとして扱う
            requestScope.getIdempotencyKey() ?: UUID.randomUUID(),
            Dateline.DEFAULT,
            requestScope.getSpoofingCurrentTime() ?: LocalDateTime.now(),
        )
        requestScope.setGameContext(gctx)
        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        return
    }

    override fun getPriority(): InterceptorPriority {
        return InterceptorPriority.HIGH
    }
}
