package net.averak.gsync.testkit.fixture.setupper

import net.averak.gsync.adapter.dao.dto.base.FriendSettingMasterDto
import net.averak.gsync.adapter.dao.mapper.base.FriendSettingMasterBaseMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.testkit.fixture.builder.master.MasterData
import org.springframework.stereotype.Component

@Component
class MasterUp(
    private val friendSettingMasterMapper: FriendSettingMasterBaseMapper,
) {

    fun setup(gctx: GameContext, vararg data: MasterData) {
        data.forEach { master ->
            if (master.friendSetting != null) {
                friendSettingMasterMapper.insert(
                    FriendSettingMasterDto(
                        gctx.masterVersion.toString(),
                        master.friendSetting.maxFriendCount.toLong(),
                        master.friendSetting.maxFriendRequestCount.toLong(),
                    ),
                )
            }
        }
    }
}
