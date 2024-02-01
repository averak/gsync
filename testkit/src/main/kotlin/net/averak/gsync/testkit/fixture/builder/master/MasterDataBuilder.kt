package net.averak.gsync.testkit.fixture.builder.master

import net.averak.gsync.domain.model.FriendSetting

data class MasterDataBuilder(
    private val data: MasterData,
) {

    constructor() : this(MasterData())

    fun friendSetting(friendSetting: FriendSetting): MasterDataBuilder {
        return copy(data = data.copy(friendSetting = friendSetting))
    }

    fun build(): MasterData {
        return data
    }
}
