package net.averak.gsync.testkit.api

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@Component
class HttpTester(
    private val webApplicationContext: WebApplicationContext,
) {

    private lateinit var mockMvc: MockMvc

    @PostConstruct
    fun init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    fun get(path: String): RequestBuilder {
        return RequestBuilder(mockMvc, MockMvcRequestBuilders.get(path))
    }

    fun post(path: String): RequestBuilder {
        return RequestBuilder(mockMvc, MockMvcRequestBuilders.post(path))
    }

    fun put(path: String): RequestBuilder {
        return RequestBuilder(mockMvc, MockMvcRequestBuilders.put(path))
    }

    fun delete(path: String): RequestBuilder {
        return RequestBuilder(mockMvc, MockMvcRequestBuilders.delete(path))
    }
}
