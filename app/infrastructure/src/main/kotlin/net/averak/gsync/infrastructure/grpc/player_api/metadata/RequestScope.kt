package net.averak.gsync.infrastructure.grpc.player_api.metadata

import io.grpc.Context
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import java.util.*

@Component
@SuppressWarnings("kotlin:S6518")
class RequestScope {

    object Attributes {

        val GAME_CONTEXT: Context.Key<GameContext> = Context.key("x-game-context")
        val PLAYER_ID: Context.Key<UUID> = Context.key("x-player-id")
        val GAME_ID: Context.Key<UUID> = Context.key("x-game-id")
    }

    val gctx: GameContext
        @Throws(NullPointerException::class)
        get() = Attributes.GAME_CONTEXT.get(Context.current())

    val playerID: UUID
        @Throws(NullPointerException::class)
        get() = Attributes.PLAYER_ID.get(Context.current())

    val gameID: UUID
        @Throws(NullPointerException::class)
        get() = Attributes.GAME_ID.get(Context.current())
}
