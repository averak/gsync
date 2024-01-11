package net.averak.gsync.testkit.api.rest

import net.averak.gsync.adapter.handler.rest.GlobalRestControllerAdvice
import net.averak.gsync.adapter.handler.rest.HttpRequestScope
import net.averak.gsync.infrastructure.json.JsonUtils
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import java.time.LocalDateTime
import java.util.*

data class RequestBuilder(
    private val mockMvc: MockMvc,
    private val builder: MockHttpServletRequestBuilder,
) {

    fun <T> execute(responseType: Class<T>? = null): Response<T> {
        val result = mockMvc.perform(builder).andReturn()
        val status = HttpStatus.valueOf(result.response.status)

        return if (status.isError) {
            if (result.response.contentAsString.isEmpty()) {
                return Response(
                    status,
                    null,
                    null,
                )
            }
            Response(
                status,
                null,
                JsonUtils.decode(result.response.contentAsString, GlobalRestControllerAdvice.ErrorResponse::class.java),
            )
        } else {
            Response(
                status,
                if (responseType == null) null else JsonUtils.decode(result.response.contentAsString, responseType),
                null,
            )
        }
    }

    fun body(value: Any): RequestBuilder {
        builder.content(JsonUtils.encode(value))
        return this
    }

    fun idempotencyKey(value: UUID): RequestBuilder {
        return header(HttpRequestScope.HeaderName.IDEMPOTENCY_KEY.key, value.toString())
    }

    fun spoofingMasterVersion(value: UUID): RequestBuilder {
        return header(HttpRequestScope.HeaderName.SPOOFING_MASTER_VERSION.key, value.toString())
    }

    fun spoofingCurrentTime(value: LocalDateTime): RequestBuilder {
        return header(HttpRequestScope.HeaderName.SPOOFING_CURRENT_TIME.key, value.toString())
    }

    private fun header(key: String, value: String): RequestBuilder {
        builder.header(key, value)
        return this
    }
}
