package net.averak.gsync.testkit.fixture.builder.master

import net.averak.gsync.domain.model.FriendSetting

data class MasterData(
    val friendSetting: FriendSetting? = null,
) {

    fun friendSetting(friendSetting: FriendSetting): MasterData {
        return copy(friendSetting = friendSetting)
    }
}
