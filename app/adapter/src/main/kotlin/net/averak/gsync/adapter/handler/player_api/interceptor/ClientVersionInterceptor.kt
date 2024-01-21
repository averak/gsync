package net.averak.gsync.adapter.handler.player_api.interceptor

import com.google.common.annotations.VisibleForTesting
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionExample
import net.averak.gsync.adapter.dao.mapper.base.RequiredClientVersionBaseMapper
import net.averak.gsync.core.config.Config
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.domain.model.Platform
import net.averak.gsync.infrastructure.grpc.player_api.metadata.IncomingHeaderKey
import net.averak.gsync.infrastructure.grpc.player_api.metadata.RequestScope
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.*

@Component
@Order(1)
class ClientVersionInterceptor(
    private val config: Config,
    private val requestScope: RequestScope,
    private val requiredClientVersionMapper: RequiredClientVersionBaseMapper,
) : ServerInterceptor {

    @SuppressWarnings("kotlin:S6518")
    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>,
    ): ServerCall.Listener<ReqT> {
        val clientVersion = headers.get(
            Metadata.Key.of(IncomingHeaderKey.CLIENT_VERSION.key, Metadata.ASCII_STRING_MARSHALLER),
        )
        val platform = headers.get(
            Metadata.Key.of(IncomingHeaderKey.PLATFORM.key, Metadata.ASCII_STRING_MARSHALLER),
        )?.let { Platform.valueOf(it) }

        // デバッグモードの場合のみ、不正なクライアントをバイパスする
        if ((clientVersion == null || platform == null) && config.debug) {
            return next.startCall(call, headers)
        }

        if (!verifyClientVersion(requestScope.gctx.masterVersion, clientVersion, platform)) {
            throw GsyncException(ErrorCode.CLIENT_VERSION_IS_NOT_SUPPORTED)
        }
        return next.startCall(call, headers)
    }

    @Throws(GsyncException::class)
    @VisibleForTesting
    fun verifyClientVersion(masterVersion: UUID, clientVersion: String?, platform: Platform?): Boolean {
        if (clientVersion == null && platform == null) {
            return true
        }

        if (clientVersion == null) {
            throw GsyncException(ErrorCode.CLIENT_VERSION_MUST_BE_SPECIFIED)
        }
        if (platform == null) {
            throw GsyncException(ErrorCode.PLATFORM_MUST_BE_SPECIFIED)
        }
        val dtos = requiredClientVersionMapper.selectByExample(
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
     * semver1 >= semver2 なら true を返す
     *
     * @param semver1 x.y.z 形式のバージョン文字列
     * @param semver2 x.y.z 形式のバージョン文字列
     */
    private fun compareSemver(semver1: String, semver2: String): Boolean {
        val major1 = semver1.split(".")[0].toInt()
        val minor1 = semver1.split(".")[1].toInt()
        val patch1 = semver1.split(".")[2].toInt()
        val major2 = semver2.split(".")[0].toInt()
        val minor2 = semver2.split(".")[1].toInt()
        val patch2 = semver2.split(".")[2].toInt()
        return major1 > major2 || (major1 == major2 && minor1 > minor2) || (major1 == major2 && minor1 == minor2 && patch1 >= patch2)
    }
}
