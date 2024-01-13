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
import java.time.LocalDateTime
import java.util.*

@Repository
open class PlayerStorageRepository(
    private val playerStorageEntryMapper: PlayerStorageEntryMapper,
    private val playerStorageRevisionMapper: PlayerStorageRevisionMapper,
) : IPlayerStorageRepository {

    override fun get(
        gctx: GameContext,
        playerID: UUID,
        tenantID: UUID,
        criteria: IPlayerStorageRepository.PlayerStorageCriteria,
    ): PlayerStorage {
        // リビジョンが存在しない場合は初期リビジョンを返却する必要があるので、まず最新のリビジョンを取得する
        val revisionDto = playerStorageRevisionMapper.selectByPlayerIdAndTenantIdOrderByCreatedAt(
            playerID.toString(),
            tenantID.toString(),
            1,
        ).firstOrNull()
        if (revisionDto == null) {
            // Repository の取得系メソッドが RW トランザクションを要求するのは認知コストが高まるので、リビジョンの初期値を INSERT せず固定値のリビジョンを返す
            return PlayerStorage.ofFirstRevision(playerID, tenantID)
        }

        if (criteria.isEmpty()) {
            return PlayerStorage(
                playerID = playerID,
                tenantID = tenantID,
                revision = UUID.fromString(revisionDto.playerStorageRevisionId),
                entries = mutableListOf(),
            )
        }
        val entries = playerStorageEntryMapper.selectByPlayerIdAndTenantId(
            playerID.toString(),
            tenantID.toString(),
            criteria.exactMatch,
            criteria.forwardMatch,
        ).map { convertDtoToModel(it) }.toMutableList()

        return PlayerStorage(
            playerID = playerID,
            tenantID = tenantID,
            revision = UUID.fromString(revisionDto.playerStorageRevisionId),
            entries = entries,
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
            playerStorage.tenantID.toString(),
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
                        .andTenantIdEqualTo(playerStorage.tenantID.toString())
                        .andKeyIn(clearedEntries.map { it.key })
                },
            )
        }

        val notClearedEntries = playerStorage.entries.filter { !it.isCleared() }
        val entryDtos = notClearedEntries.map {
            convertModelToDto(it, playerStorage.playerID, playerStorage.tenantID, gctx.currentTime, gctx.currentTime)
        }
        playerStorageEntryMapper.syncOriginal(entryDtos)
        playerStorageEntryMapper.insertOrUpdate(entryDtos)
    }

    private fun convertDtoToModel(playerStorageEntryDto: PlayerStorageEntryDto): PlayerStorageEntry {
        return PlayerStorageEntry(
            key = playerStorageEntryDto.key,
            value = playerStorageEntryDto.value,
        )
    }

    private fun convertModelToDto(
        playerStorageEntry: PlayerStorageEntry,
        playerID: UUID,
        tenantID: UUID,
        createdAt: LocalDateTime,
        updatedAt: LocalDateTime,
    ): PlayerStorageEntryDto {
        return PlayerStorageEntryDto(
            playerID.toString(),
            tenantID.toString(),
            playerStorageEntry.key,
            createdAt,
            updatedAt,
            playerStorageEntry.value,
        )
    }
}
