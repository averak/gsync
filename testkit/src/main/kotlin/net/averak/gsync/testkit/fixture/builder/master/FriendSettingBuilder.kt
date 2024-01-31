package net.averak.gsync.testkit.fixture.builder.master

import net.averak.gsync.domain.model.FriendSetting

class FriendSettingBuilder(
    private var data: FriendSetting,
) {

    constructor() : this(
        FriendSetting(
            maxFriendCount = 100,
            maxFriendRequestCount = 100,
        ),
    )

    fun maxFriendCount(maxFriendCount: Int): FriendSettingBuilder {
        data = data.copy(maxFriendCount = maxFriendCount)
        return this
    }

    fun maxFriendRequestCount(maxFriendRequestCount: Int): FriendSettingBuilder {
        data = data.copy(maxFriendRequestCount = maxFriendRequestCount)
        return this
    }

    fun build(): FriendSetting {
        return data
    }
}
