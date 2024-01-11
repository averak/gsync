package net.averak.gsync.core.logger

import net.averak.gsync.core.config.Config
import net.averak.gsync.core.game_context.GameContext
import net.logstash.logback.argument.StructuredArgument
import net.logstash.logback.argument.StructuredArguments
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Logger(
    private val config: Config,
) {

    private val logger = LoggerFactory.getLogger(Logger::class.java)

    fun info(gctx: GameContext, message: String) {
        this.logger.info(
            message,
            makeServerInfoPayload(),
            makeGameContextPayload(gctx),
        )
    }

    fun info(gctx: GameContext, message: String, payload: Map<String, Any>) {
        this.logger.info(
            message,
            makeServerInfoPayload(),
            makeGameContextPayload(gctx),
            StructuredArguments.value("payload", payload),
        )
    }

    fun warn(gctx: GameContext, exception: Exception) {
        this.logger.warn(
            exception.toString(),
            makeServerInfoPayload(),
            makeGameContextPayload(gctx),
            StructuredArguments.value("exception", exception),
        )
    }

    fun error(exception: Exception) {
        this.logger.error(
            exception.toString(),
            makeServerInfoPayload(),
            StructuredArguments.value("exception", exception),
        )
    }

    fun error(gctx: GameContext, exception: Exception) {
        this.logger.error(
            exception.toString(),
            makeServerInfoPayload(),
            makeGameContextPayload(gctx),
            StructuredArguments.value("exception", exception),
        )
    }

    private fun makeGameContextPayload(gctx: GameContext): StructuredArgument {
        return StructuredArguments.value(
            "game_context",
            mapOf(
                "idempotencyKey" to gctx.idempotencyKey.toString(),
                "currentTime" to gctx.currentTime.toString(),
            ),
        )
    }

    private fun makeServerInfoPayload(): StructuredArgument {
        return StructuredArguments.value(
            "server",
            mapOf(
                "version" to config.version,
            ),
        )
    }
}
