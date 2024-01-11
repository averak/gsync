package net.averak.gsync.testkit.api

import jakarta.annotation.PostConstruct
import net.averak.gsync.adapter.handler.rest.GlobalRestControllerAdvice
import net.averak.gsync.adapter.handler.rest.HttpRequestScope
import net.averak.gsync.infrastructure.json.JsonUtils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.time.LocalDateTime
import java.util.*

@Component
class HttpTester(
    private val webApplicationContext: WebApplicationContext,
) {

    private lateinit var mockMvc: MockMvc

    @PostConstruct
    fun init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    fun get(path: String): HttpRequestBuilder {
        return HttpRequestBuilder(mockMvc, MockMvcRequestBuilders.get(path))
    }

    fun post(path: String): HttpRequestBuilder {
        return HttpRequestBuilder(mockMvc, MockMvcRequestBuilders.post(path))
    }

    fun put(path: String): HttpRequestBuilder {
        return HttpRequestBuilder(mockMvc, MockMvcRequestBuilders.put(path))
    }

    fun delete(path: String): HttpRequestBuilder {
        return HttpRequestBuilder(mockMvc, MockMvcRequestBuilders.delete(path))
    }
}

data class HttpTesterResponse<T>(
    val status: HttpStatus,
    val body: T?,
    val error: GlobalRestControllerAdvice.ErrorResponse?,
)

data class HttpRequestBuilder(
    private val mockMvc: MockMvc,
    private val builder: MockHttpServletRequestBuilder,
) {

    fun <T> execute(responseType: Class<T>? = null): HttpTesterResponse<T> {
        val result = mockMvc.perform(builder).andReturn()
        val status = HttpStatus.valueOf(result.response.status)

        return if (status.isError) {
            if (result.response.contentAsString.isEmpty()) {
                return HttpTesterResponse(
                    status,
                    null,
                    null,
                )
            }
            HttpTesterResponse(
                status,
                null,
                JsonUtils.decode(result.response.contentAsString, GlobalRestControllerAdvice.ErrorResponse::class.java),
            )
        } else {
            HttpTesterResponse(
                status,
                if (responseType == null) null else JsonUtils.decode(result.response.contentAsString, responseType),
                null,
            )
        }
    }

    fun body(value: Any): HttpRequestBuilder {
        builder.content(JsonUtils.encode(value))
        return this
    }

    fun idempotencyKey(value: UUID): HttpRequestBuilder {
        return header(HttpRequestScope.HeaderName.IDEMPOTENCY_KEY.key, value.toString())
    }

    fun spoofingMasterVersion(value: UUID): HttpRequestBuilder {
        return header(HttpRequestScope.HeaderName.SPOOFING_MASTER_VERSION.key, value.toString())
    }

    fun spoofingCurrentTime(value: LocalDateTime): HttpRequestBuilder {
        return header(HttpRequestScope.HeaderName.SPOOFING_CURRENT_TIME.key, value.toString())
    }

    private fun header(key: String, value: String): HttpRequestBuilder {
        builder.header(key, value)
        return this
    }
}
