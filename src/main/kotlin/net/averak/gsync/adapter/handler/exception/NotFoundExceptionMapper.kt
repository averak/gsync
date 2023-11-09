package net.averak.gsync.adapter.handler.exception

import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import net.averak.gsync.core.exception.ErrorCode

@Provider
class NotFoundExceptionMapper : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException?): Response {
        return Response
            .status(Response.Status.NOT_FOUND)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .entity(ErrorResponse(ErrorCode.NOT_FOUND_API))
            .build()
    }
}
