package net.averak.gsync.adapter.handler.exception

import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import net.averak.gsync.core.exception.ErrorCode
import org.apache.http.HttpStatus

@Provider
class NotFoundExceptionMapper : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException?): Response {
        return Response.status(HttpStatus.SC_NOT_FOUND).entity(ErrorResponse(ErrorCode.NOT_FOUND_API)).build()
    }
}
