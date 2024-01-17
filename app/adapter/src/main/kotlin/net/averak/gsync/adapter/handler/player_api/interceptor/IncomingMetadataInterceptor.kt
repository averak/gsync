package net.averak.gsync.adapter.handler.player_api.interceptor

import io.grpc.*
import net.averak.gsync.adapter.handler.player_api.mdval.IncomingHeaderKey
import net.averak.gsync.adapter.handler.player_api.mdval.RequestScope
import net.averak.gsync.adapter.handler.utils.MasterVersionUtils
import net.averak.gsync.core.config.Config
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.game_context.GameContext
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
@Order(0)
class IncomingMetadataInterceptor(
    private val config: Config,
    private val masterVersionUtils: MasterVersionUtils,
) : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>,
    ): ServerCall.Listener<ReqT> {
        // デバッグモードの場合のみ、なりすましを許容する
        val spoofingMasterVersion =
            if (config.debug) {
                headers[Metadata.Key.of(IncomingHeaderKey.DEBUG_SPOOFING_MASTER_VERSION.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
                    UUID.fromString(it)
                }
            } else {
                null
            }
        val spoofingCurrentTime =
            if (config.debug) {
                headers[Metadata.Key.of(IncomingHeaderKey.DEBUG_SPOOFING_CURRENT_TIME.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
                    LocalDateTime.parse(it)
                }
            } else {
                null
            }

        val playerID = headers[Metadata.Key.of(IncomingHeaderKey.DEBUG_SPOOFING_PLAYER_ID.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
            UUID.fromString(it)
        }
        val gameID = headers[Metadata.Key.of(IncomingHeaderKey.GAME_ID.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
            UUID.fromString(it)
        }
        val idempotencyKey =
            headers[Metadata.Key.of(IncomingHeaderKey.IDEMPOTENCY_KEY.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
                UUID.fromString(it)
            }

        val gctx = GameContext(
            masterVersionUtils.getEnabledMasterVersion(spoofingMasterVersion),
            // クライアントが Idempotency-Key を必ず設定してくるとは限らないので、未設定の場合はサーバ側でユニークキーを発行し、毎回異なるリクエストとして扱う
            idempotencyKey ?: UUID.randomUUID(),
            Dateline.DEFAULT,
            spoofingCurrentTime ?: LocalDateTime.now(),
        )

        val context = Context.current()
            .withValue(RequestScope.Attributes.GAME_CONTEXT, gctx)
            .withValue(RequestScope.Attributes.PLAYER_ID, playerID)
            .withValue(RequestScope.Attributes.GAME_ID, gameID)
        return Contexts.interceptCall(context, call, headers, next)
    }
}
