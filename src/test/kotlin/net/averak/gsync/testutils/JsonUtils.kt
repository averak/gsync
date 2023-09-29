package net.averak.gsync.testutils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

class JsonUtils {

    companion object {

        private val objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)

        @JvmStatic
        fun toJson(value: Any?): String {
            return if (value == null) {
                "{}"
            } else {
                objectMapper.writeValueAsString(value)
            }
        }

        @JvmStatic
        fun <T> fromJson(json: String, clazz: Class<T>): T {
            return objectMapper.readValue(json, clazz)
        }

    }

}