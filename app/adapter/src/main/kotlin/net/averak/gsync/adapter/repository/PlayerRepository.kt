package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.dao.dto.base.PlayerLoginDto
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileDto
import net.averak.gsync.adapter.dao.mapper.base.PlayerLoginBaseMapper
import net.averak.gsync.adapter.dao.mapper.base.PlayerProfileBaseMapper
import net.averak.gsync.adapter.dao.mapper.extend.PlayerMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Player
import net.averak.gsync.domain.model.PlayerLogin
import net.averak.gsync.domain.model.PlayerProfile
import net.averak.gsync.domain.repository.IPlayerRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class PlayerRepository(
    private val playerMapper: PlayerMapper,
    private val playerProfileMapper: PlayerProfileBaseMapper,
    private val playerLoginMapper: PlayerLoginBaseMapper,
) : IPlayerRepository {

    override fun get(gctx: GameContext, id: UUID): Player? {
        val dto = playerMapper.selectByPlayerId(id.toString())
        return if (dto == null) {
            null
        } else {
            val loginDto = dto.playerLogin
            Player(
                id = UUID.fromString(dto.playerId),
                friendID = UUID.fromString(dto.friendId),
                isBanned = dto.isBanned,
                profile = PlayerProfile(
                    nickname = dto.playerProfile.nickname,
                    iconID = dto.playerProfile.iconId,
                ),
                login = loginDto?.let {
                    PlayerLogin(
                        totalLoginDays = loginDto.totalLoginDays.toInt(),
                        lastLoggedInAt = loginDto.lastLoggedInAt,
                    )
                },
            )
        }
    }

    override fun save(gctx: GameContext, player: Player) {
        // クエリ数が多いので、プロフィールとログイン履歴は別モデルに切り出した方が良いかもしれない
        val dto = playerMapper.selectByPrimaryKey(player.id.toString())
        if (dto == null) {
            playerMapper.insert(
                PlayerDto(
                    player.id.toString(),
                    player.friendID.toString(),
                    player.isBanned,
                    gctx.currentTime,
                    gctx.currentTime,
                ),
            )
        } else {
            playerMapper.updateByPrimaryKey(
                PlayerDto(
                    player.id.toString(),
                    player.friendID.toString(),
                    player.isBanned,
                    dto.createdAt,
                    gctx.currentTime,
                ),
            )
        }

        val profileDto = playerProfileMapper.selectByPrimaryKey(player.id.toString())
        if (profileDto == null) {
            playerProfileMapper.insert(
                PlayerProfileDto(
                    player.id.toString(),
                    player.profile.nickname,
                    player.profile.iconID,
                    gctx.currentTime,
                    gctx.currentTime,
                ),
            )
        } else {
            playerProfileMapper.updateByPrimaryKey(
                PlayerProfileDto(
                    player.id.toString(),
                    player.profile.nickname,
                    player.profile.iconID,
                    profileDto.createdAt,
                    gctx.currentTime,
                ),
            )
        }

        // player.login が null の場合はログイン履歴を削除するのが適切な永続化の振る舞いだが、
        // ログイン履歴を削除するようなユースケースはない & プレイヤーには見れない情報であり多少の誤差は許容されるため、削除処理はスキップする (実装が面倒なのもある)
        val login = player.login ?: return
        val loginDto = playerLoginMapper.selectByPrimaryKey(player.id.toString())
        if (loginDto == null) {
            playerLoginMapper.insert(
                PlayerLoginDto(
                    player.id.toString(),
                    login.totalLoginDays.toLong(),
                    login.lastLoggedInAt,
                    gctx.currentTime,
                    gctx.currentTime,
                ),
            )
        } else {
            playerLoginMapper.updateByPrimaryKey(
                PlayerLoginDto(
                    player.id.toString(),
                    login.totalLoginDays.toLong(),
                    login.lastLoggedInAt,
                    loginDto.createdAt,
                    gctx.currentTime,
                ),
            )
        }
    }
}
