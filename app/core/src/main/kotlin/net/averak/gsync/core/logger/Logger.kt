package net.averak.gsync.core.logger

import net.averak.gsync.core.config.Config
import net.logstash.logback.argument.StructuredArgument
import net.logstash.logback.argument.StructuredArguments
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Logger {

    @Autowired
    private fun init(config: Config) {
        Logger.config = config
    }

    companion object {

        private lateinit var config: Config

        private val logger = LoggerFactory.getLogger(Logger::class.java)

        fun info(payload: Any? = null) {
            val structuredArgs = mutableListOf<StructuredArgument>()
            structuredArgs.add(makeServerInfoPayload())
            if (payload != null) {
                structuredArgs.add(StructuredArguments.value("payload", payload))
            }
            logger.info("", *structuredArgs.toTypedArray())
        }

        @Suppress("DuplicatedCode")
        fun warn(exception: Exception? = null, payload: Any? = null) {
            val structuredArgs = mutableListOf<StructuredArgument>()
            structuredArgs.add(makeServerInfoPayload())
            if (payload != null) {
                structuredArgs.add(StructuredArguments.value("payload", payload))
            }
            if (exception != null) {
                structuredArgs.add(StructuredArguments.value("exception", exception))
            }
            logger.warn("", *structuredArgs.toTypedArray())
        }

        @Suppress("DuplicatedCode")
        fun error(exception: Exception? = null, payload: Any? = null) {
            val structuredArgs = mutableListOf<StructuredArgument>()
            structuredArgs.add(makeServerInfoPayload())
            if (payload != null) {
                structuredArgs.add(StructuredArguments.value("payload", payload))
            }
            if (exception != null) {
                structuredArgs.add(StructuredArguments.value("exception", exception))
            }
            logger.error("", *structuredArgs.toTypedArray())
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
}
