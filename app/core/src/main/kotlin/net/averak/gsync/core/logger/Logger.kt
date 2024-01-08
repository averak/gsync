package net.averak.gsync.core.logger

import net.averak.gsync.core.gamecontext.GameContext
import net.logstash.logback.argument.StructuredArgument
import net.logstash.logback.argument.StructuredArguments
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Logger {

    private val logger = LoggerFactory.getLogger(Logger::class.java)

    fun info(gctx: GameContext, message: String) {
        this.logger.info(
            message,
            makeServerInfoPayload(gctx),
            makeGameContextPayload(gctx),
        )
    }

    fun info(gctx: GameContext, message: String, payload: Map<String, Any>) {
        this.logger.info(
            message,
            makeServerInfoPayload(gctx),
            makeGameContextPayload(gctx),
            StructuredArguments.value("payload", payload),
        )
    }

    fun warn(gctx: GameContext, exception: Exception) {
        this.logger.warn(
            exception.toString(),
            makeServerInfoPayload(gctx),
            makeGameContextPayload(gctx),
            StructuredArguments.value("exception", exception),
        )
    }

    fun error(gctx: GameContext, exception: Exception) {
        this.logger.error(
            exception.toString(),
            makeServerInfoPayload(gctx),
            makeGameContextPayload(gctx),
            StructuredArguments.value("exception", exception),
        )
    }

    private fun makeGameContextPayload(gctx: GameContext): StructuredArgument {
        return StructuredArguments.value(
            "game_context",
            mapOf(
                "idempotencyKey" to gctx.idempotencyKey,
                "currentTime" to gctx.currentTime.toString(),
            ),
        )
    }

    private fun makeServerInfoPayload(gctx: GameContext): StructuredArgument {
        return StructuredArguments.value(
            "server",
            mapOf(
                "version" to gctx.serverVersion,
            ),
        )
    }
}
