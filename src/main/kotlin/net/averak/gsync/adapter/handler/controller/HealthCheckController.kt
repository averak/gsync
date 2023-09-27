package net.averak.gsync.adapter.handler.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/api/health")
class HealthCheckController {

    @GET
    fun health() {
    }

}
