package net.averak.gsync.usecase

import net.averak.gsync.core.gamecontext.GameContext
import net.averak.gsync.domain.model.Echo
import org.springframework.stereotype.Service

@Service
class EchoUsecase {

    fun echo(gctx: GameContext, message: String): Echo {
        return Echo(message, gctx.currentTime)
    }
}
