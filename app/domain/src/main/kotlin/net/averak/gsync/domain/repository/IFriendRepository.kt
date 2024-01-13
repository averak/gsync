package net.averak.gsync.domain.repository

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.*
import net.averak.gsync.domain.repository.exception.MasterDataNotFoundException
import java.util.*

interface IFriendRepository {

    fun getFriends(gctx: GameContext, playerID: UUID): List<Friend>

    fun getFriendStatus(gctx: GameContext, playerID: UUID, vararg friendPlayerIDs: UUID): List<FriendStatus>

    fun getSentFriendRequests(gctx: GameContext, playerID: UUID): List<FriendRequest>

    fun getReceivedFriendRequests(gctx: GameContext, playerID: UUID): List<FriendRequest>

    fun saveFriendship(gctx: GameContext, friendship: Friendship)

    fun saveFriendRequest(gctx: GameContext, friendRequest: FriendRequest)

    fun deleteFriendship(gctx: GameContext, friendship: Friendship)

    @Throws(MasterDataNotFoundException::class)
    fun getSetting(gctx: GameContext): FriendSetting
}
