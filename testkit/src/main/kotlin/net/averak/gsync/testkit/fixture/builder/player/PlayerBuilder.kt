package net.averak.gsync.testkit.fixture.builder.player

import net.averak.gsync.domain.model.Player
import net.averak.gsync.domain.model.PlayerProfile
import net.averak.gsync.testkit.Faker
import java.util.*

class PlayerBuilder(
    private var data: Player,
) {

    constructor(playerID: UUID) : this(
        Player(
            id = playerID,
            friendID = UUID.randomUUID(),
            isBanned = false,
            profile = PlayerProfile(
                nickname = Faker.alphanumeric(10),
                iconID = Faker.uuidv4().toString(),
            ),
            login = null,
        ),
    )

    fun profile(profile: PlayerProfile): PlayerBuilder {
        data = data.copy(profile = profile)
        return this
    }

    fun build(): Registry {
        return Registry(player = data)
    }
}
