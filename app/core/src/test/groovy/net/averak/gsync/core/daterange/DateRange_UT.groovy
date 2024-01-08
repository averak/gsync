package net.averak.gsync.core.daterange

import net.averak.gsync.testkit.AbstractSpec

import java.time.LocalDate
import java.time.LocalDateTime

class DateRange_UT extends AbstractSpec {

    def "constructor: 日時、Datelineからインスタンスを作成できる"() {
        when:
        final result = new DateRange(time, dateline)

        then:
        result == expectedResult

        where:
        time                                     | dateline                     || expectedResult
        LocalDateTime.of(2000, 1, 1, 0, 0, 0)    | new Dateline(false, 0, 0, 0) || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 0, 0, 0), LocalDateTime.of(2000, 1, 2, 0, 0, 0))
        LocalDateTime.of(2000, 1, 1, 23, 59, 59) | new Dateline(false, 0, 0, 0) || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 0, 0, 0), LocalDateTime.of(2000, 1, 2, 0, 0, 0))
    }

    def "constructor: 日時からインスタンスを作成できる (デフォルトのDatelineが適応される)"() {
        when:
        final result = new DateRange(time)

        then:
        result == expectedResult

        where:
        time                                     || expectedResult
        LocalDateTime.of(2000, 1, 1, 0, 0, 0)    || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(1999, 12, 31, 21, 0, 0), LocalDateTime.of(2000, 1, 1, 21, 0, 0))
        LocalDateTime.of(2000, 1, 1, 12, 59, 59) || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(1999, 12, 31, 21, 0, 0), LocalDateTime.of(2000, 1, 1, 21, 0, 0))
    }

    def "includes: 指定された日時が含まれるか判定"() {
        given:
        final dateRange = new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 13, 0, 0), LocalDateTime.of(2000, 1, 2, 13, 0, 0))

        when:
        final result = dateRange.includes(time)

        then:
        result == expectedResult

        where:
        time                                     || expectedResult
        LocalDateTime.of(2000, 1, 1, 12, 59, 59) || false
        LocalDateTime.of(2000, 1, 1, 13, 0, 0)   || true
        LocalDateTime.of(2000, 1, 2, 12, 59, 59) || true
        LocalDateTime.of(2000, 1, 2, 13, 0, 0)   || false
    }

    def "addDays: 日数を加減算できる"() {
        given:
        final dateRange = new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 13, 0, 0), LocalDateTime.of(2000, 1, 2, 13, 0, 0))

        when:
        final result = dateRange.addDays(n)

        then:
        result == expectedResult

        where:
        n  || expectedResult
        -1 || new DateRange(LocalDate.of(1999, 12, 31), LocalDateTime.of(1999, 12, 31, 13, 0, 0), LocalDateTime.of(2000, 1, 1, 13, 0, 0))
        0  || new DateRange(LocalDate.of(2000, 1, 1), LocalDateTime.of(2000, 1, 1, 13, 0, 0), LocalDateTime.of(2000, 1, 2, 13, 0, 0))
        1  || new DateRange(LocalDate.of(2000, 1, 2), LocalDateTime.of(2000, 1, 2, 13, 0, 0), LocalDateTime.of(2000, 1, 3, 13, 0, 0))
    }

}
