package net.averak.gsync.testkit.fixture.builder.player

import net.averak.gsync.domain.model.PlayerStorage
import net.averak.gsync.domain.model.PlayerStorageEntry
import java.util.*

class PlayerStorageBuilder(
    private var data: PlayerStorage,
) {

    constructor(playerID: UUID, gameID: UUID) : this(
        PlayerStorage(
            playerID = playerID,
            gameID = gameID,
            revision = UUID.randomUUID(),
            entries = mutableListOf(
                PlayerStorageEntry(
                    key = "key1",
                    value = "value1".toByteArray(),
                ),
                PlayerStorageEntry(
                    key = "key2",
                    value = "value2".toByteArray(),
                ),
            ),
        ),
    )

    fun revision(revision: UUID): PlayerStorageBuilder {
        data = data.copy(revision = revision)
        return this
    }

    fun entries(vararg entries: PlayerStorageEntry): PlayerStorageBuilder {
        data = data.copy(entries = entries.toMutableList())
        return this
    }

    fun build(): PlayerStorage {
        return data
    }
}
