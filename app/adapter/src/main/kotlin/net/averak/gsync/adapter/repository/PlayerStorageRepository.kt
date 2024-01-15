package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryExample
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionExample
import net.averak.gsync.adapter.dao.mapper.extend.PlayerStorageEntryMapper
import net.averak.gsync.adapter.dao.mapper.extend.PlayerStorageRevisionMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.domain.repository.exception.AlreadyDoneException
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class PlayerStorageRepository(
    private val playerStorageEntryMapper: PlayerStorageEntryMapper,
    private val playerStorageRevisionMapper: PlayerStorageRevisionMapper,
) : IPlayerStorageRepository {

    override fun get(
        gctx: GameContext,
        playerID: UUID,
        gameID: UUID,
        criteria: IPlayerStorageRepository.PlayerStorageCriteria,
    ): PlayerStorage {
        // リビジョンが存在しない場合は初期リビジョンを返却する必要があるので、まず最新のリビジョンを取得する
        val revisionDto = playerStorageRevisionMapper.selectByPlayerIdAndGameIdOrderByCreatedAt(
            playerID.toString(),
            gameID.toString(),
            1,
        ).firstOrNull()
        if (revisionDto == null) {
            // Repository の取得系メソッドが RW トランザクションを要求するのは認知コストが高まるので、リビジョンの初期値を INSERT せず固定値のリビジョンを返す
            return PlayerStorage.ofFirstRevision(playerID, gameID)
        }

        if (criteria.isEmpty()) {
            return PlayerStorage(
                playerID = playerID,
                gameID = gameID,
                revision = UUID.fromString(revisionDto.playerStorageRevisionId),
                entries = mutableListOf(),
            )
        }
        val entryDtos = playerStorageEntryMapper.selectByPlayerIdAndGameId(
            playerID.toString(),
            gameID.toString(),
            criteria.exactMatch,
            criteria.forwardMatch,
        )

        return convertDtoToModel(
            playerID,
            gameID,
            revisionDto,
            entryDtos,
        )
    }

    override fun save(gctx: GameContext, playerStorage: PlayerStorage) {
        // 冪等性を保証するために、リビジョンの更新時には idempotencyKey を検証する
        if (playerStorageRevisionMapper.countByExample(
                PlayerStorageRevisionExample().apply {
                    createCriteria().andIdempotencyKeyEqualTo(gctx.idempotencyKey.toString())
                },
            ) > 0
        ) {
            throw AlreadyDoneException()
        }
        val revisionDto = PlayerStorageRevisionDto(
            playerStorage.playerID.toString(),
            playerStorage.gameID.toString(),
            playerStorage.revision.toString(),
            gctx.idempotencyKey.toString(),
            gctx.currentTime,
            gctx.currentTime,
        )
        playerStorageRevisionMapper.syncOriginal(revisionDto)
        playerStorageRevisionMapper.insertOrUpdate(revisionDto)

        // 値が空のエントリは削除する
        val clearedEntries = playerStorage.entries.filter { it.isCleared() }
        if (clearedEntries.isNotEmpty()) {
            playerStorageEntryMapper.deleteByExample(
                PlayerStorageEntryExample().apply {
                    createCriteria().andPlayerIdEqualTo(playerStorage.playerID.toString())
                        .andGameIdEqualTo(playerStorage.gameID.toString())
                        .andKeyIn(clearedEntries.map { it.key })
                },
            )
        }

        val notClearedEntries = playerStorage.entries.filter { !it.isCleared() }
        val entryDtos = notClearedEntries.map {
            PlayerStorageEntryDto(
                playerStorage.playerID.toString(),
                playerStorage.gameID.toString(),
                it.key,
                gctx.currentTime,
                gctx.currentTime,
                it.value,
            )
        }
        playerStorageEntryMapper.syncOriginal(entryDtos)
        playerStorageEntryMapper.insertOrUpdate(entryDtos)
    }

    companion object {

        fun convertDtoToModel(
            playerID: UUID,
            gameID: UUID,
            revisionDto: PlayerStorageRevisionDto,
            entryDtos: List<PlayerStorageEntryDto>,
        ): PlayerStorage {
            return PlayerStorage(
                playerID = playerID,
                gameID = gameID,
                revision = UUID.fromString(revisionDto.playerStorageRevisionId),
                entries = entryDtos.map { convertDtoToModel(it) }.toMutableList(),
            )
        }

        fun convertDtoToModel(dto: PlayerStorageEntryDto): PlayerStorageEntry {
            return PlayerStorageEntry(
                key = dto.key,
                value = dto.value,
            )
        }
    }
}
