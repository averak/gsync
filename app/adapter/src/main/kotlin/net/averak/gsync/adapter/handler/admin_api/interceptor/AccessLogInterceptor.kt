package net.averak.gsync.adapter.handler.admin_api.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.gsync.adapter.handler.admin_api.HttpRequestScope
import net.averak.gsync.core.logger.Logger
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import java.time.Duration
import java.time.LocalDateTime

/**
 * アクセスログを出力するインターセプター
 */
@Component
class AccessLogInterceptor(
    private val logger: Logger,
    private val requestScope: HttpRequestScope,
) : IRequestInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        val gctx = requestScope.getGameContext()
        logger.info(
            gctx,
            "access log",
            mapOf(
                "http_request" to mapOf(
                    "client_version" to requestScope.getClientVersion(),
                    "idempotency_key" to gctx.idempotencyKey.toString(),
                    "requested_at" to gctx.currentTime.toString(),
                    "elapsed_ms" to Duration.between(gctx.currentTime, LocalDateTime.now()).toMillis(),
                    "status_code" to HttpStatusCode.valueOf(response.status),
                    "method" to request.method,
                    "path" to request.requestURI,
                    "query_string" to request.queryString,
                    "ip_address" to request.remoteAddr,
                ),
            ),
        )
    }

    override fun getPriority(): InterceptorPriority = InterceptorPriority.LOW
}
