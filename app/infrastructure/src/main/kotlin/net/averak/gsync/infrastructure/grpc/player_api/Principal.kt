package net.averak.gsync.infrastructure.grpc.player_api

import java.util.*

data class Principal(
    val playerID: UUID,
    val gameID: UUID,
)
