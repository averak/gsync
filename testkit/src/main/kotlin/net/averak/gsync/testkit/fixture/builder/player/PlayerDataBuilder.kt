package net.averak.gsync.testkit.fixture.builder.player

import net.averak.gsync.domain.model.*
import net.averak.gsync.testkit.Faker
import java.util.*

data class PlayerDataBuilder(
    private val data: PlayerData,
) {

    constructor(playerID: UUID) : this(
        PlayerData(
            player = Player(
                id = playerID,
                friendID = UUID.randomUUID(),
                isBanned = false,
                profile = PlayerProfile(
                    nickname = Faker.alphanumeric(10),
                    iconID = Faker.uuidv4().toString(),
                ),
                login = null,
            ),
        ),
    )

    fun profile(profile: PlayerProfile): PlayerDataBuilder {
        return copy(data = data.copy(player = data.player.copy(profile = profile)))
    }

    fun playerStorage(playerStorage: PlayerStorage): PlayerDataBuilder {
        return copy(data = data.copy(playerStorage = playerStorage))
    }

    fun friendships(vararg friendships: Friendship): PlayerDataBuilder {
        return copy(data = data.copy(friendships = friendships.toMutableList()))
    }

    fun friendRequests(vararg friendRequests: FriendRequest): PlayerDataBuilder {
        return copy(data = data.copy(friendRequests = friendRequests.toMutableList()))
    }

    fun build(): PlayerData {
        return data
    }
}
