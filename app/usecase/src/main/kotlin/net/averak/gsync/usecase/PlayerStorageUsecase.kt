package net.averak.gsync.usecase

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.domain.repository.exception.AlreadyDoneException
import net.averak.gsync.usecase.transaction.ITransaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerStorageUsecase(
    private val transaction: ITransaction,
    private val playerStorageRepository: IPlayerStorageRepository,
) {

    fun search(gctx: GameContext, playerID: UUID, tenantID: UUID, criteria: IPlayerStorageRepository.PlayerStorageCriteria): PlayerStorage {
        return transaction.roTx {
            return@roTx playerStorageRepository.get(gctx, playerID, tenantID, criteria)
        }
    }

    fun set(gctx: GameContext, playerID: UUID, tenantID: UUID, entry: PlayerStorageEntry, revision: UUID): SetResult {
        val criteria = IPlayerStorageRepository.PlayerStorageCriteria(
            listOf(entry.key),
            listOf(),
        )

        return transaction.rwTx {
            val playerStorage = playerStorageRepository.get(gctx, playerID, tenantID, criteria)
            playerStorage.validate(revision)
            playerStorage.set(entry)
            try {
                playerStorageRepository.save(gctx, playerStorage)
            } catch (e: AlreadyDoneException) {
                return@rwTx SetResult(entry, revision)
            }
            return@rwTx SetResult(entry, playerStorage.revision)
        }
    }

    fun clear(
        gctx: GameContext,
        playerID: UUID,
        tenantID: UUID,
        revision: UUID,
        criteria: IPlayerStorageRepository.PlayerStorageCriteria,
    ): UUID {
        return transaction.rwTx {
            val playerStorage = playerStorageRepository.get(gctx, playerID, tenantID, criteria)
            playerStorage.validate(revision)
            playerStorage.clearAll()
            try {
                playerStorageRepository.save(gctx, playerStorage)
            } catch (e: AlreadyDoneException) {
                return@rwTx revision
            }
            return@rwTx playerStorage.revision
        }
    }

    data class SetResult(
        val entry: PlayerStorageEntry,
        val revision: UUID,
    )
}
