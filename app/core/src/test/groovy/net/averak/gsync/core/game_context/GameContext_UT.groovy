package net.averak.gsync.core.game_context

import net.averak.gsync.core.daterange.DateRange
import net.averak.gsync.core.daterange.Dateline
import net.averak.gsync.testkit.AbstractSpec
import net.averak.gsync.testkit.Faker

import java.time.LocalDate
import java.time.LocalDateTime

class GameContext_UT extends AbstractSpec {

    def "getToday: 今日の日付を取得する"() {
        given:
        final context = new GameContext(
            Faker.uuidv4(),
            Faker.uuidv4(),
            new Dateline(false, 0, 0, 0),
            LocalDateTime.of(2020, 1, 1, 0, 0, 0),
        )

        when:
        final result = context.getToday()

        then:
        result == new DateRange(
            LocalDate.of(2020, 1, 1),
            LocalDateTime.of(2020, 1, 1, 0, 0, 0),
            LocalDateTime.of(2020, 1, 2, 0, 0, 0),
        )
    }

}
