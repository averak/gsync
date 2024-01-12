package net.averak.gsync.usecase

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.domain.repository.PlayerStorageCriteria
import net.averak.gsync.usecase.transaction.ITransaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerStorageUsecase(
    private val transaction: ITransaction,
    private val playerStorageRepository: IPlayerStorageRepository,
) {

    fun search(gctx: GameContext, playerID: UUID, tenantID: UUID, criteria: PlayerStorageCriteria): PlayerStorage {
        return transaction.roTx {
            return@roTx playerStorageRepository.get(gctx, playerID, tenantID, criteria)
        }
    }

    fun set(gctx: GameContext, playerID: UUID, tenantID: UUID, entry: PlayerStorageEntry, revision: UUID): SetResult {
        val criteria = PlayerStorageCriteria(
            listOf(entry.key),
            listOf(),
        )

        return transaction.rwTx {
            val playerStorage = playerStorageRepository.get(gctx, playerID, tenantID, criteria)
            playerStorage.validate(revision)
            playerStorage.set(entry)
            playerStorageRepository.save(gctx, playerStorage)
            return@rwTx SetResult(entry, playerStorage.revision)
        }
    }

    fun clear(gctx: GameContext, playerID: UUID, tenantID: UUID, revision: UUID, criteria: PlayerStorageCriteria): UUID {
        return transaction.rwTx {
            val playerStorage = playerStorageRepository.get(gctx, playerID, tenantID, criteria)
            playerStorage.validate(revision)
            playerStorage.clearAll()
            playerStorageRepository.save(gctx, playerStorage)
            return@rwTx playerStorage.revision
        }
    }

    data class SetResult(
        val entry: PlayerStorageEntry,
        val revision: UUID,
    )
}
