package net.averak.gsync.usecase

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Echo
import net.averak.gsync.domain.repository.IEchoRepository
import net.averak.gsync.usecase.transaction.ITransaction
import org.springframework.stereotype.Service

@Service
class EchoUsecase(
    private val transaction: ITransaction,
    private val echoRepository: IEchoRepository,
) {

    fun echo(gctx: GameContext, message: String): Echo {
        return transaction.rwTx {
            val echo = Echo(message, gctx.currentTime)
            echoRepository.save(gctx, echo)
            return@rwTx echo
        }
    }
}
