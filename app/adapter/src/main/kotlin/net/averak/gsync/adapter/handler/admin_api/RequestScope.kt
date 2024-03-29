package net.averak.gsync.adapter.handler.admin_api

import jakarta.servlet.http.HttpServletRequest
import net.averak.gsync.core.config.Config
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Platform
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class RequestScope(
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
    enum class HeaderName(val key: String) {

        CLIENT_VERSION("x-client-version"),
        PLATFORM("x-platform"),
        IDEMPOTENCY_KEY("x-idempotency-key"),

        // 以下はデバッグモードの場合のみ有効になる
        SPOOFING_MASTER_VERSION("x-spoofing-master-version"),
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

    fun getPlatform(): Platform? {
        val value = httpServletRequest.getHeader(HeaderName.PLATFORM.key)
        return if (value == null) {
            null
        } else {
            // HTTP ヘッダーは大文字小文字を区別しないので、大文字に変換する必要がある
            Platform.valueOf(value.uppercase())
        }
    }

    fun getIdempotencyKey(): UUID? {
        return httpServletRequest.getHeader(HeaderName.IDEMPOTENCY_KEY.key)?.let {
            UUID.fromString(it)
        }
    }

    fun getSpoofingMasterVersion(): UUID? {
        return if (config.debug) {
            httpServletRequest.getHeader(HeaderName.SPOOFING_MASTER_VERSION.key)?.let {
                UUID.fromString(it)
            }
        } else {
            null
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
