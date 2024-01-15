package net.averak.gsync.adapter.handler.player_api.pbconv

import com.google.protobuf.ByteString
import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import net.averak.gsync.domain.repository.IPlayerStorageRepository
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSearchV1
import net.averak.gsync.schema.protobuf.player_api.PlayerStorageSetV1
import net.averak.gsync.schema.protobuf.resource.player_storage.Criteria
import net.averak.gsync.schema.protobuf.resource.player_storage.Entry
import net.averak.gsync.usecase.PlayerStorageUsecase

class PlayerStorageConverter {

    companion object {

        @JvmStatic
        fun fromPb(criteria: Criteria): IPlayerStorageRepository.PlayerStorageCriteria {
            when (criteria.matchingPattern) {
                Criteria.MatchingType.EXACT_MATCH -> {
                    return IPlayerStorageRepository.PlayerStorageCriteria(
                        exactMatch = listOf(criteria.pattern),
                        forwardMatch = listOf(),
                    )
                }

                Criteria.MatchingType.FORWARD_MATCH -> {
                    return IPlayerStorageRepository.PlayerStorageCriteria(
                        exactMatch = listOf(),
                        forwardMatch = listOf(criteria.pattern),
                    )
                }

                Criteria.MatchingType.UNRECOGNIZED -> {
                    throw IllegalArgumentException("MatchingType is not set")
                }

                null -> {
                    throw IllegalArgumentException("MatchingType is not set")
                }
            }
        }

        @JvmStatic
        fun fromPb(entry: Entry): PlayerStorageEntry {
            return PlayerStorageEntry(
                key = entry.key,
                value = entry.value.toByteArray(),
            )
        }

        @JvmStatic
        fun toPb(entry: PlayerStorageEntry): Entry {
            return Entry.newBuilder()
                .setKey(entry.key)
                .setValue(ByteString.copyFrom(entry.value))
                .build()
        }

        @JvmStatic
        fun toPb(result: PlayerStorageUsecase.SetResult): PlayerStorageSetV1.Response {
            return PlayerStorageSetV1.Response.newBuilder()
                .setEntry(toPb(result.entry))
                .setNextRevision(result.revision.toString())
                .build()
        }

        @JvmStatic
        fun toPb(playerStorage: PlayerStorage): PlayerStorageSearchV1.Response {
            return PlayerStorageSearchV1.Response.newBuilder()
                .addAllEntries(playerStorage.entries.map { toPb(it) })
                .build()
        }
    }
}
