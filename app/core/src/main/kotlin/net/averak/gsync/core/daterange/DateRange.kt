package net.averak.gsync.core.daterange

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * ゲーム内の「o月x日」を表す時刻範囲オブジェクト
 *
 * [from, to) の半開区間のため to は含まない
 */
data class DateRange(
    val date: LocalDate,
    val from: LocalDateTime,
    val to: LocalDateTime,
) {

    constructor(time: LocalDateTime) : this(time, Dateline.DEFAULT)

    constructor(time: LocalDateTime, dateline: Dateline) : this(dateline.getDateRangeAtTime(time))

    private constructor(dateRange: DateRange) : this(dateRange.date, dateRange.from, dateRange.to)

    fun includes(time: LocalDateTime): Boolean {
        return from <= time && time < to
    }

    fun addDays(n: Int): DateRange {
        return DateRange(date.plusDays(n.toLong()), from.plusDays(n.toLong()), to.plusDays(n.toLong()))
    }
}
