package net.averak.gsync.core.daterange

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * サービス内で日付が変更される境界線を表すオブジェクト (UTC)
 * -12:00:00 から +12:00:00 までの値をとる
 *
 * 例) Dateline が 00:00:00 の場合
 * UTC => 2000-01-01 00:00:00 ~ 2000-01-02 00:00:00
 * JST => 2000-01-01 09:00:00 ~ 2000-01-02 09:00:00
 *
 * 例) Dateline が +05:00:00 の場合
 * UTC => 2000-01-01 05:00:00 ~ 2000-01-02 05:00:00
 * JST => 2000-01-01 14:00:00 ~ 2000-01-02 14:00:00
 *
 * 例) Dateline が -05:00:00 の場合
 * UTC => 1999-12-23 19:00:00 ~ 2000-01-01 19:00:00
 * JST => 2000-01-01 04:00:00 ~ 2000-01-02 04:00:00
 */
data class Dateline(
    val isMinus: Boolean,
    val hour: Int,
    val minute: Int,
    val second: Int,
) {

    companion object {

        // 22:00 (JST) に注文を締め切ることから、デフォルトの日付変更境界線は -11:00:00 (UTC) とする
        // JST だと -02:00:00 なので、UTC だと -02:00:00 - 09:00:00 = -11:00:00 になる
        @JvmStatic
        val DEFAULT = Dateline(true, 11, 0, 0)
    }

    init {
        require(LocalTime.of(hour, minute, second) <= LocalTime.of(12, 0, 0)) {
            "time must be -12:00:00 ~ +12:00:00, but was $this"
        }
    }

    fun getDateRangeOnDate(on: LocalDate): DateRange {
        var start = LocalDateTime.of(on, LocalTime.of(0, 0, 0))
        if (isMinus) {
            start = start.minusHours(hour.toLong())
            start = start.minusMinutes(minute.toLong())
            start = start.minusSeconds(second.toLong())
        } else {
            start = start.plusHours(hour.toLong())
            start = start.plusMinutes(minute.toLong())
            start = start.plusSeconds(second.toLong())
        }
        val end = start.plusDays(1)
        return DateRange(on, start, end)
    }

    fun getDateRangeAtTime(at: LocalDateTime): DateRange {
        val dateRange = getDateRangeOnDate(at.toLocalDate())
        if (dateRange.from.isAfter(at)) {
            return getDateRangeOnDate(at.minusDays(1).toLocalDate())
        }
        if (dateRange.to.isBefore(at)) {
            return getDateRangeOnDate(at.plusDays(1).toLocalDate())
        }
        return dateRange
    }

    override fun toString(): String {
        return if (isMinus) {
            String.format("-%02d:%02d:%02d", hour, minute, second)
        } else {
            String.format("+%02d:%02d:%02d", hour, minute, second)
        }
    }
}
