package net.averak.gsync.adapter.handler.player_api.scope

import io.grpc.Context
import net.averak.gsync.core.game_context.GameContext
import org.springframework.stereotype.Component
import java.util.*

@Component
@SuppressWarnings("kotlin:S6518")
class RequestScope {

    val gctx: GameContext
        get() = try {
            RequestScopeAttributes.GAME_CONTEXT.get(Context.current())
        } catch (_: NullPointerException) {
            throw GrpcMetadataNotFoundException("Game context is not found in request scope.")
        }

    val playerID: UUID
        get() = try {
            RequestScopeAttributes.PLAYER_ID.get(Context.current())
        } catch (_: NullPointerException) {
            throw GrpcMetadataNotFoundException("PlayerID is not found in request scope.")
        }

    val gameID: UUID
        get() = try {
            RequestScopeAttributes.GAME_ID.get(Context.current())
        } catch (_: NullPointerException) {
            throw GrpcMetadataNotFoundException("GameID is not found in request scope.")
        }
}
