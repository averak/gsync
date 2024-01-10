package net.averak.gsync.adapter.handler.rest.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.gsync.adapter.handler.rest.HttpRequestScope
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import java.util.*

@Component
open class GameContextInterceptor(
    private val requestScope: HttpRequestScope,
) : IRequestInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val spoofingCurrentTime = requestScope.getSpoofingCurrentTime()
        val gctx = GameContext(
            // クライアントが Idempotency-Key を必ず設定してくるとは限らないので、未設定の場合はサーバ側でユニークキーを発行し、毎回異なるリクエストとして扱う
            requestScope.getIdempotencyKey() ?: UUID.randomUUID(),
            Dateline.DEFAULT,
            spoofingCurrentTime ?: LocalDateTime.now(),
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
