package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.GameDto
import net.averak.gsync.adapter.dao.dto.base.GameExample
import net.averak.gsync.adapter.dao.mapper.base.GameBaseMapper
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Game
import net.averak.gsync.domain.repository.IGameRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class GameRepository(
    private val gameMapper: GameBaseMapper,
) : IGameRepository {

    override fun findByIDs(gctx: GameContext, ids: List<UUID>): List<Game> {
        if (ids.isEmpty()) {
            return emptyList()
        }

        return gameMapper.selectByExample(
            GameExample().apply {
                createCriteria().andGameIdIn(ids.map { it.toString() })
            },
        ).map {
            convertDtoToModel(it)
        }
    }

    override fun save(gctx: GameContext, game: Game) {
        val dto = GameDto(
            game.id.toString(),
            game.name,
        )
        gameMapper.syncOriginal(dto)
        gameMapper.insertOrUpdate(dto)
    }

    companion object {

        fun convertDtoToModel(dto: GameDto): Game {
            return Game(
                id = UUID.fromString(dto.gameId),
                name = dto.name,
            )
        }
    }
}
