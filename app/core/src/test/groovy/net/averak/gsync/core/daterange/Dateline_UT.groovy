package net.averak.gsync.core.daterange

import net.averak.gsync.testkit.AbstractSpec

import java.time.LocalDate
import java.time.LocalDateTime

class Dateline_UT extends AbstractSpec {

    def "constructor: -12:00:00 ~ +12:00:00 の間で作成できる"() {
        when:
        final result = new Dateline(isMinus, hour, minute, second)

        then:
        result.isMinus() == isMinus
        result.hour == hour
        result.minute == minute
        result.second == second

        where:
        isMinus | hour | minute | second
        true    | 0    | 0      | 0
        true    | 12   | 0      | 0
        false   | 0    | 0      | 0
        false   | 12   | 0      | 0
    }

    def "constructor: 日時の範囲が不正な場合は例外を返す"() {
        when:
        new Dateline(isMinus, hour, minute, second)

        then:
        final exception = thrown(IllegalArgumentException)
        exception.message == expectedExceptionMessage

        then:
        where:
        isMinus | hour | minute | second || expectedExceptionMessage
        true    | 13   | 0      | 0      || "time must be -12:00:00 ~ +12:00:00, but was -13:00:00"
        true    | 12   | 1      | 0      || "time must be -12:00:00 ~ +12:00:00, but was -12:01:00"
        true    | 12   | 0      | 1      || "time must be -12:00:00 ~ +12:00:00, but was -12:00:01"
        false   | 13   | 0      | 0      || "time must be -12:00:00 ~ +12:00:00, but was +13:00:00"
        false   | 12   | 1      | 0      || "time must be -12:00:00 ~ +12:00:00, but was +12:01:00"
        false   | 12   | 0      | 1      || "time must be -12:00:00 ~ +12:00:00, but was +12:00:01"
    }

    def "getDateRangeOnDate: 日付から DateRange を取得できる"() {
        when:
        final result = dateline.getDateRangeOnDate(on)

        then:
        result == expectedResult

        where:
        dateline                     | on                       || expectedResult
        new Dateline(false, 0, 0, 0) | LocalDate.of(2000, 1, 1) || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 0, 0, 0), LocalDateTime.of(2000, 1, 2, 0, 0, 0))
        new Dateline(false, 0, 0, 0) | LocalDate.of(2000, 1, 2) || new DateRange(LocalDate.of(2000, 1, 2), LocalDateTime.of(2000, 1, 2, 0, 0, 0), LocalDateTime.of(2000, 1, 3, 0, 0, 0))
        new Dateline(true, 1, 0, 0)  | LocalDate.of(2000, 1, 1) || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(1999, 12, 31, 23, 0, 0), LocalDateTime.of(2000, 1, 1, 23, 0, 0))
    }

    def "getDateRangeAtTime: 日時から DateRange を取得できる"() {
        when:
        final result = dateline.getDateRangeAtTime(at)

        then:
        result == expectedResult

        where:
        dateline                     | at                                       || expectedResult
        new Dateline(false, 0, 0, 0) | LocalDateTime.of(2000, 1, 1, 0, 0, 0)    || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 0, 0, 0), LocalDateTime.of(2000, 1, 2, 0, 0, 0))
        new Dateline(false, 0, 0, 0) | LocalDateTime.of(2000, 1, 1, 23, 59, 59) || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 0, 0, 0), LocalDateTime.of(2000, 1, 2, 0, 0, 0))
        new Dateline(false, 0, 0, 0) | LocalDateTime.of(2000, 1, 2, 0, 0, 0)    || new DateRange(LocalDate.of(2000, 1, 2), LocalDateTime.of(2000, 1, 2, 0, 0, 0), LocalDateTime.of(2000, 1, 3, 0, 0, 0))
        new Dateline(false, 1, 0, 0) | LocalDateTime.of(2000, 1, 1, 0, 59, 59)  || new DateRange(LocalDate.of(1999, 12, 31), LocalDateTime.of(1999, 12, 31, 1, 0, 0), LocalDateTime.of(2000, 1, 1, 1, 0, 0))
        new Dateline(true, 1, 0, 0)  | LocalDateTime.of(2000, 1, 1, 0, 0, 0)    || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(1999, 12, 31, 23, 0, 0), LocalDateTime.of(2000, 1, 1, 23, 0, 0))
        new Dateline(true, 1, 0, 0)  | LocalDateTime.of(2000, 1, 1, 23, 0, 1)   || new DateRange(LocalDate.of(2000, 1, 2), LocalDateTime.of(2000, 1, 1, 23, 0, 0), LocalDateTime.of(2000, 1, 2, 23, 0, 0))
    }
}
