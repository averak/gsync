package net.averak.gsync.infrastructure.grpc.player_api

import com.google.protobuf.AbstractMessage
import net.averak.gsync.core.game_context.GameContext

data class Request<T : AbstractMessage>(
    val gctx: GameContext,
    val principal: Principal?,
    val message: T,
) {

    fun mustPrincipal(): Principal {
        // TODO: #92 IllegalArgumentException ではなく、適切な例外を投げること
        require(principal != null) {
            "Principal is required"
        }
        return principal
    }
}
