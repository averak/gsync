package net.averak.gsync.adapter.handler.player_api.scope

import io.grpc.Context
import net.averak.gsync.core.game_context.GameContext
import java.util.*

object RequestScopeAttributes {

    val GAME_CONTEXT: Context.Key<GameContext> = Context.key("x-game-context")
    val PLAYER_ID: Context.Key<UUID> = Context.key("x-player-id")
    val GAME_ID: Context.Key<UUID> = Context.key("x-game-id")
}
