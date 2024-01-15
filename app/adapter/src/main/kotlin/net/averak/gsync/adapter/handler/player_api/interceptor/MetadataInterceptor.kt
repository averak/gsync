package net.averak.gsync.adapter.handler.player_api.interceptor

import com.google.common.annotations.VisibleForTesting
import io.grpc.*
import net.averak.gsync.adapter.dao.dto.base.MasterVersionExample
import net.averak.gsync.adapter.dao.mapper.base.MasterVersionBaseMapper
import net.averak.gsync.adapter.handler.player_api.scope.IncomingMD
import net.averak.gsync.adapter.handler.player_api.scope.RequestScope
import net.averak.gsync.adapter.handler.player_api.scope.RequestScopeAttributes
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class MetadataInterceptor(
    private val requestScope: RequestScope,
    private val masterVersionMapper: MasterVersionBaseMapper,
) : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>,
    ): ServerCall.Listener<ReqT> {
        // TODO: デバッグモードの場合のみ、デバッグ用のメタデータを読み取る
        val spoofingMasterVersion =
            headers[Metadata.Key.of(IncomingMD.DEBUG_SPOOFING_MASTER_VERSION.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
                UUID.fromString(it)
            }
        val idempotencyKey =
            headers[Metadata.Key.of(IncomingMD.IDEMPOTENCY_KEY.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
                UUID.fromString(it)
            }
        val spoofingCurrentTime =
            headers[Metadata.Key.of(IncomingMD.DEBUG_SPOOFING_CURRENT_TIME.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
                LocalDateTime.parse(it)
            }
        val gctx = GameContext(
            getEnabledMasterVersion(spoofingMasterVersion),
            // クライアントが Idempotency-Key を必ず設定してくるとは限らないので、未設定の場合はサーバ側でユニークキーを発行し、毎回異なるリクエストとして扱う
            idempotencyKey ?: UUID.randomUUID(),
            Dateline.DEFAULT,
            spoofingCurrentTime ?: LocalDateTime.now(),
        )

        val playerID = headers[Metadata.Key.of(IncomingMD.DEBUG_SPOOFING_PLAYER_ID.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
            UUID.fromString(it)
        }
        val gameID = headers[Metadata.Key.of(IncomingMD.GAME_ID.key, Metadata.ASCII_STRING_MARSHALLER)]?.let {
            UUID.fromString(it)
        }

        val context = Context.current()
            .withValue(RequestScopeAttributes.GAME_CONTEXT, gctx)
            .withValue(RequestScopeAttributes.PLAYER_ID, playerID)
            .withValue(RequestScopeAttributes.GAME_ID, gameID)
        return Contexts.interceptCall(context, call, headers, next)
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
}
