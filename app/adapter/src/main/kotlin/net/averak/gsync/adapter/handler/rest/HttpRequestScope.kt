package net.averak.gsync.adapter.handler.rest

import jakarta.servlet.http.HttpServletRequest
import net.averak.gsync.core.config.Config
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class HttpRequestScope(
    private val config: Config,
    private val httpServletRequest: HttpServletRequest,
) {

    /**
     * インターセプターで書き込まれる属性のキー
     */
    private enum class AttributeKey(val key: String) {

        GAME_CONTEXT("x-game-context"),
    }

    /**
     * カスタムヘッダー名
     */
    private enum class HeaderName(val key: String) {

        CLIENT_VERSION("x-client-version"),
        IDEMPOTENCY_KEY("x-idempotency-key"),

        // 以下はデバッグモードの場合のみ有効になる
        SPOOFING_CURRENT_TIME("x-spoofing-current-time"),
    }

    fun setGameContext(gctx: GameContext) {
        httpServletRequest.setAttribute(AttributeKey.GAME_CONTEXT.key, gctx)
    }

    @Throws(HttpMetadataNotFoundException::class)
    fun getGameContext(): GameContext {
        val gctx = httpServletRequest.getAttribute(AttributeKey.GAME_CONTEXT.key)?.let { it as GameContext }
        if (gctx == null) {
            throw HttpMetadataNotFoundException("Game context is not found in request scope.")
        }
        return gctx
    }

    fun getClientVersion(): String? {
        return httpServletRequest.getHeader(HeaderName.CLIENT_VERSION.key)
    }

    fun getIdempotencyKey(): UUID? {
        return httpServletRequest.getHeader(HeaderName.IDEMPOTENCY_KEY.key)?.let {
            UUID.fromString(it)
        }
    }

    fun getSpoofingCurrentTime(): LocalDateTime? {
        return if (config.debug) {
            httpServletRequest.getHeader(HeaderName.SPOOFING_CURRENT_TIME.key)?.let {
                LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME)
            }
        } else {
            null
        }
    }
}

class HttpMetadataNotFoundException(message: String) : Exception(message)
