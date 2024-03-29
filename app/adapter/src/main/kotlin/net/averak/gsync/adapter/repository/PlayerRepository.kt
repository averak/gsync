package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.PlayerDto
import net.averak.gsync.adapter.dao.dto.base.PlayerLoginDto
import net.averak.gsync.adapter.dao.dto.base.PlayerProfileDto
import net.averak.gsync.adapter.dao.dto.extend.PlayerILDto
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
            convertDtoToModel(dto)
        }
    }

    override fun save(gctx: GameContext, player: Player) {
        // クエリ数が多いので、プロフィールとログイン履歴は別モデルに切り出した方が良いかもしれない
        val dto = PlayerDto(
            player.id.toString(),
            player.isBanned,
        )
        playerMapper.syncOriginal(dto)
        playerMapper.insertOrUpdate(dto)

        val profileDto = PlayerProfileDto(
            player.id.toString(),
            player.profile.nickname,
            player.profile.iconID,
        )
        playerProfileMapper.syncOriginal(profileDto)
        playerProfileMapper.insertOrUpdate(profileDto)

        // player.login が null の場合はログイン履歴を削除するのが適切な永続化の振る舞いだが、
        // ログイン履歴を削除するようなユースケースはない & プレイヤーには見れない情報であり多少の誤差は許容されるため、削除処理はスキップする (実装が面倒なのもある)
        val login = player.login
        if (login == null) {
            return
        }
        val loginDto = PlayerLoginDto(
            player.id.toString(),
            login.totalLoginDays.toLong(),
            login.lastLoggedInAt,
        )
        playerLoginMapper.syncOriginal(loginDto)
        playerLoginMapper.insertOrUpdate(loginDto)
    }

    companion object {

        fun convertDtoToModel(dto: PlayerILDto): Player {
            return Player(
                id = UUID.fromString(dto.playerId),
                isBanned = dto.isBanned,
                profile = convertDtoToModel(dto.playerProfile),
                login = dto.playerLogin?.let {
                    PlayerLogin(
                        totalLoginDays = it.totalLoginDays.toInt(),
                        lastLoggedInAt = it.lastLoggedInAt,
                    )
                },
            )
        }

        fun convertDtoToModel(dto: PlayerProfileDto): PlayerProfile {
            return PlayerProfile(
                nickname = dto.nickname,
                iconID = dto.iconId,
            )
        }
    }
}
