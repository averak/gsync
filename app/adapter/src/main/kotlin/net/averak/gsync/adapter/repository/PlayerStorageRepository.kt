package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryDto
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageEntryExample
import net.averak.gsync.adapter.dao.dto.base.PlayerStorageRevisionDto
import net.averak.gsync.adapter.dao.mapper.extend.PlayerStorageEntryMapper
import net.averak.gsync.adapter.dao.mapper.extend.PlayerStorageRevisionMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.domain.repository.PlayerStorageCriteria
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
open class PlayerStorageRepository(
    private val playerStorageEntryMapper: PlayerStorageEntryMapper,
    private val playerStorageRevisionMapper: PlayerStorageRevisionMapper,
) : IPlayerStorageRepository {

    override fun get(gctx: GameContext, playerID: UUID, tenantID: UUID, criteria: PlayerStorageCriteria): PlayerStorage {
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
        ).map { it.toModel() }.toMutableList()

        return PlayerStorage(
            playerID = playerID,
            tenantID = tenantID,
            revision = UUID.fromString(revisionDto.playerStorageRevisionId),
            entries = entries,
        )
    }

    override fun save(gctx: GameContext, playerStorage: PlayerStorage) {
        // 値が空のエントリは削除する
        val clearedEntryKeys = playerStorage.entries.filter { it.isCleared() }.map { it.key }
        if (clearedEntryKeys.isNotEmpty()) {
            playerStorageEntryMapper.deleteByExample(
                PlayerStorageEntryExample().apply {
                    createCriteria().andPlayerIdEqualTo(playerStorage.playerID.toString())
                        .andTenantIdEqualTo(playerStorage.tenantID.toString())
                        .andKeyIn(clearedEntryKeys)
                },
            )
        }

        val notClearedEntries = playerStorage.entries.filter { !it.isCleared() }
        val entryDtos = playerStorageEntryMapper.selectByPlayerIdAndTenantId(
            playerStorage.playerID.toString(),
            playerStorage.tenantID.toString(),
            notClearedEntries.map { it.key },
            listOf(),
        )
        val insertEntries = notClearedEntries.filter { entry ->
            entryDtos.none { it.key == entry.key }
        }
        if (insertEntries.isNotEmpty()) {
            playerStorageEntryMapper.bulkInsert(
                insertEntries.map {
                    convertModelToDto(it, playerStorage.playerID, playerStorage.tenantID, gctx.currentTime, gctx.currentTime)
                },
            )
        }
        val updateEntries = notClearedEntries.filter { entry ->
            entryDtos.any { it.key == entry.key }
        }
        if (updateEntries.isNotEmpty()) {
            updateEntries.parallelStream().forEach {
                playerStorageEntryMapper.updateByPrimaryKeyWithBLOBs(
                    convertModelToDto(
                        it,
                        playerStorage.playerID,
                        playerStorage.tenantID,
                        entryDtos.first { dto -> dto.key == it.key }.createdAt,
                        gctx.currentTime,
                    ),
                )
            }
        }

        val revisionDto = playerStorageRevisionMapper.selectByPrimaryKey(
            playerStorage.playerID.toString(),
            playerStorage.tenantID.toString(),
            playerStorage.revision.toString(),
        )
        if (revisionDto == null) {
            playerStorageRevisionMapper.insert(
                PlayerStorageRevisionDto(
                    playerStorage.playerID.toString(),
                    playerStorage.tenantID.toString(),
                    playerStorage.revision.toString(),
                    gctx.currentTime,
                    gctx.currentTime,
                ),
            )
        } else {
            playerStorageRevisionMapper.updateByPrimaryKey(
                PlayerStorageRevisionDto(
                    playerStorage.playerID.toString(),
                    playerStorage.tenantID.toString(),
                    playerStorage.revision.toString(),
                    revisionDto.createdAt,
                    gctx.currentTime,
                ),
            )
        }
    }

    private fun PlayerStorageEntryDto.toModel(): PlayerStorageEntry {
        return PlayerStorageEntry(
            key = this.key,
            value = this.value,
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
