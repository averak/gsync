package net.averak.gsync.adapter.handler.rest

import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.infrastructure.json.JsonUtils
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.util.MultiValueMap
import spock.lang.Shared

import java.time.LocalDateTime

abstract class AbstractController_IT extends AbstractDatabaseSpec {

    @Autowired
    private MockMvc mockMvc

    @Shared
    protected GameContext gctx

    /**
     * GET request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder getRequest(final String path) {
        return MockMvcRequestBuilders.get(path)
    }

    /**
     * POST request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path) {
        return MockMvcRequestBuilders.post(path)
    }

    /**
     * POST request (Form)
     *
     * @param path path
     * @param params query params
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path, final MultiValueMap<String, String> params) {
        return MockMvcRequestBuilders.post(path)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .params(params)
    }

    /**
     * POST request (JSON)
     *
     * @param path path
     * @param content request body
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder postRequest(final String path, final Object content) {
        return MockMvcRequestBuilders.post(path)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JsonUtils.encode(content))
    }

    /**
     * PUT request (JSON)
     *
     * @param path path
     * @param content request body
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder putRequest(final String path, final Object content) {
        return MockMvcRequestBuilders.put(path)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(JsonUtils.encode(content))
    }

    /**
     * DELETE request
     *
     * @param path path
     *
     * @return HTTP request builder
     */
    MockHttpServletRequestBuilder deleteRequest(final String path) {
        return MockMvcRequestBuilders.delete(path)
    }

    /**
     * Execute request
     *
     * @param request HTTP request builder
     * @param status expected HTTP status
     *
     * @return MVC result
     */
    MvcResult execute(final MockHttpServletRequestBuilder request, final HttpStatus status) {
        final result = mockMvc.perform(request).andReturn()

        assert result.response.status == status.value()
        return result
    }

    /**
     * Execute request / return response
     *
     * @param request HTTP request builder
     * @param status expected HTTP status
     * @param clazz response class
     *
     * @return response
     */
    def <T> T execute(final MockHttpServletRequestBuilder request, final HttpStatus status, final Class<T> clazz) {
        final result = mockMvc.perform(request).andReturn()

        assert result.response.status == status.value()
        return JsonUtils.decode(result.getResponse().getContentAsString(), clazz)
    }

    /**
     * Execute request / verify exception
     *
     * @param request HTTP request builder
     * @param exception expected exception
     *
     * @return error response
     */
    GlobalRestControllerAdvice.ErrorResponse execute(final MockHttpServletRequestBuilder request, final HttpStatus expectedHttpStatus, final GsyncException ex) {
        final result = mockMvc.perform(request).andReturn()

        final response = JsonUtils.decode(result.response.contentAsString, GlobalRestControllerAdvice.ErrorResponse.class)
        assert result.response.status == expectedHttpStatus.value()
        assert response.code == ex.errorCode.name()
        assert response.message == ex.errorCode.summary
        return response
    }

    /**
     * setup before test case
     */
    void setup() {
        this.gctx = new GameContext(
            Faker.uuidv4(),
            Dateline.DEFAULT,
            LocalDateTime.now(),
        )
    }
}
