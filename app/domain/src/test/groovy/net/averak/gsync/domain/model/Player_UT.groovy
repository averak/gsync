package net.averak.gsync.domain.model

import net.averak.gsync.testkit.AbstractSpec
import net.averak.gsync.testkit.Faker

import java.time.LocalDateTime

class PlayerProfile_UT extends AbstractSpec {

    def "init: バリデーション"() {
        when:
        new PlayerProfile(
            nickname,
            Faker.uuidv4().toString(),
        )

        then:
        final ex = thrown(IllegalArgumentException)
        ex.message == expectedExceptionMessage

        where:
        nickname      || expectedExceptionMessage
        ""            || "nickname must be 1 to 10 characters long, but was ``."
        " "           || "nickname must be 1 to 10 characters long, but was ` `."
        "aaaaaaaaaaa" || "nickname must be 1 to 10 characters long, but was `aaaaaaaaaaa`."
    }
}

class PlayerLogin_UT extends AbstractSpec {

    def "init: バリデーション"() {
        when:
        new PlayerLogin(
            totalLoginDays,
            Faker.fake(LocalDateTime),
        )

        then:
        final ex = thrown(IllegalArgumentException)
        ex.message == expectedExceptionMessage

        where:
        totalLoginDays || expectedExceptionMessage
        -1             || "totalLoginDays must be greater than or equal to 0, but was `-1`."
    }
}
