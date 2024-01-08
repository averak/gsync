package net.averak.gsync.core.game_context

import net.averak.gsync.core.daterange.DateRange
import net.averak.gsync.core.daterange.Dateline
import java.time.LocalDateTime
import java.util.*

/**
 * 機能によらずアプリケーション横断的なコンテキスト
 */
data class GameContext(
    val serverVersion: String,
    val idempotencyKey: UUID,
    val dateline: Dateline,
    val currentTime: LocalDateTime,
) {

    fun getToday(): DateRange {
        return dateline.getDateRangeAtTime(currentTime)
    }
}
