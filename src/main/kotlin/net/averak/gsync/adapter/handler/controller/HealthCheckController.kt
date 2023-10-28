package net.averak.gsync.adapter.handler.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import org.jboss.resteasy.reactive.RestResponse

@Path("/api/health")
class HealthCheckController {
    @GET
    fun health(): RestResponse<Unit> {
        return RestResponse.ok()
    }
}
