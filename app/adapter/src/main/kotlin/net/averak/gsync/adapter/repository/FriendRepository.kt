package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.*
import net.averak.gsync.adapter.dao.mapper.base.FriendSettingMasterBaseMapper
import net.averak.gsync.adapter.dao.mapper.base.PlayerFriendBaseMapper
import net.averak.gsync.adapter.dao.mapper.base.PlayerFriendRequestBaseMapper
import net.averak.gsync.adapter.dao.mapper.base.PlayerProfileBaseMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.*
import net.averak.gsync.domain.repository.IFriendRepository
import net.averak.gsync.domain.repository.exception.MasterDataNotFoundException
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class FriendRepository(
    private val playerFriendMapper: PlayerFriendBaseMapper,
    private val playerFriendRequestMapper: PlayerFriendRequestBaseMapper,
    private val playerProfileMapper: PlayerProfileBaseMapper,
    private val friendSettingMasterMapper: FriendSettingMasterBaseMapper,
) : IFriendRepository {

    override fun getFriends(gctx: GameContext, playerID: UUID): List<Friend> {
        // becomeFriendAt > now だとしても、フレンドと判定して良い
        val dtos = playerFriendMapper.selectByExample(
            PlayerFriendExample().apply {
                createCriteria().andPlayerIdEqualTo(playerID.toString())
            },
        )
        if (dtos.isEmpty()) {
            return emptyList()
        }

        val profiles = playerProfileMapper.selectByExample(
            PlayerProfileExample().apply {
                createCriteria().andPlayerIdIn(dtos.map { it.friendPlayerId })
            },
        )

        return dtos.map { dto ->
            val profile = profiles.find { it.playerId == dto.friendPlayerId }
            if (profile == null) {
                throw RuntimeException("Player profile not found, playerID=${dto.friendPlayerId}.")
            }
            convertDtoToModel(dto, profile)
        }
    }

    override fun getFriendStatus(gctx: GameContext, playerID: UUID, vararg friendPlayerIDs: UUID): List<FriendStatus> {
        if (friendPlayerIDs.isEmpty()) {
            return emptyList()
        }

        val friends = getFriends(gctx, playerID)
        val requestDtos = playerFriendRequestMapper.selectByExample(
            PlayerFriendRequestExample().apply {
                createCriteria()
                    .andPlayerIdIn(listOf(playerID.toString(), *friendPlayerIDs.map { it.toString() }.toTypedArray()))
            },
        )

        return friendPlayerIDs.map { friendPlayerID ->
            return@map when {
                friends.any { it.friendPlayerID == friendPlayerID } -> {
                    FriendStatus.FRIEND
                }

                requestDtos.any { it.playerId == playerID.toString() && it.receiverPlayerId == friendPlayerID.toString() } -> {
                    FriendStatus.SENT_REQUEST
                }

                requestDtos.any { it.playerId == friendPlayerID.toString() && it.receiverPlayerId == playerID.toString() } -> {
                    FriendStatus.RECEIVED_REQUEST
                }

                else -> {
                    FriendStatus.NONE
                }
            }
        }
    }

    override fun getSentFriendRequests(gctx: GameContext, playerID: UUID): List<FriendRequest> {
        val dtos = playerFriendRequestMapper.selectByExample(
            PlayerFriendRequestExample().apply {
                createCriteria().andPlayerIdEqualTo(playerID.toString())
            },
        )
        return dtos.map { convertDtoToModel(it) }
    }

    override fun getReceivedFriendRequests(gctx: GameContext, playerID: UUID): List<FriendRequest> {
        val dtos = playerFriendRequestMapper.selectByExample(
            PlayerFriendRequestExample().apply {
                createCriteria().andReceiverPlayerIdEqualTo(playerID.toString())
            },
        )
        return dtos.map { convertDtoToModel(it) }
    }

    override fun saveFriendship(gctx: GameContext, friendship: Friendship) {
        val dtos = listOf(
            PlayerFriendDto(
                friendship.playerIDs.first.toString(),
                friendship.playerIDs.second.toString(),
                friendship.becomeFriendAt,
                gctx.currentTime,
                gctx.currentTime,
            ),
            PlayerFriendDto(
                friendship.playerIDs.second.toString(),
                friendship.playerIDs.first.toString(),
                friendship.becomeFriendAt,
                gctx.currentTime,
                gctx.currentTime,
            ),
        )
        playerFriendMapper.syncOriginal(dtos)
        playerFriendMapper.insertOrUpdate(dtos)

        // フレンド関係が構築されたら、フレンドリクエストは削除する
        playerFriendRequestMapper.deleteByExample(
            PlayerFriendRequestExample().apply {
                createCriteria()
                    .andPlayerIdIn(
                        friendship.playerIDs.toList().map { it.toString() },
                    )
                    .andReceiverPlayerIdIn(
                        friendship.playerIDs.toList().map { it.toString() },
                    )
            },
        )
    }

    override fun saveFriendRequest(gctx: GameContext, friendRequest: FriendRequest) {
        val dto = PlayerFriendRequestDto(
            friendRequest.playerID.toString(),
            friendRequest.receiverPlayerID.toString(),
            friendRequest.sentAt,
            gctx.currentTime,
            gctx.currentTime,
        )
        playerFriendRequestMapper.syncOriginal(dto)
        playerFriendRequestMapper.insertOrUpdate(dto)
    }

    override fun deleteFriendship(gctx: GameContext, friendship: Friendship) {
        val example = PlayerFriendExample().apply {
            createCriteria()
                .andPlayerIdIn(
                    friendship.playerIDs.toList().map { it.toString() },
                )
                .andFriendPlayerIdIn(
                    friendship.playerIDs.toList().map { it.toString() },
                )
        }
        playerFriendMapper.deleteByExample(example)
    }

    override fun getSetting(gctx: GameContext): FriendSetting {
        val dto = friendSettingMasterMapper.selectByPrimaryKey(gctx.masterVersion.toString())
        if (dto == null) {
            throw MasterDataNotFoundException(FriendSettingMasterDto::class.java)
        }

        return convertDtoToModel(dto)
    }

    companion object {

        fun convertDtoToModel(dto: PlayerFriendDto, profileDto: PlayerProfileDto): Friend {
            return Friend(
                playerID = UUID.fromString(dto.playerId),
                friendPlayerID = UUID.fromString(dto.friendPlayerId),
                becomeFriendAt = dto.becomeFriendAt,
                profile = PlayerRepository.convertDtoToModel(profileDto),
            )
        }

        fun convertDtoToModel(dto: PlayerFriendRequestDto): FriendRequest {
            return FriendRequest(
                playerID = UUID.fromString(dto.playerId),
                receiverPlayerID = UUID.fromString(dto.receiverPlayerId),
                sentAt = dto.sentAt,
            )
        }

        fun convertDtoToModel(dto: FriendSettingMasterDto): FriendSetting {
            return FriendSetting(
                maxFriendCount = dto.maxFriendCount.toInt(),
                maxFriendRequestCount = dto.maxFriendRequestCount.toInt(),
            )
        }
    }
}
