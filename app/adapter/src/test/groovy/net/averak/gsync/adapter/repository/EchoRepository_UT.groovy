package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.entity.base.EchoEntity
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Echo
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

import java.time.LocalDateTime

class EchoRepository_UT extends AbstractRepository_UT {

    @Autowired
    EchoRepository sut

    @Shared
    LocalDateTime now = LocalDateTime.now()

    def "save: PKが存在しない場合は作成される"() {
        given:
        final echo = Faker.fake(Echo)

        when:
        this.sut.save(Faker.fake(GameContext), echo)

        then:
        with(sql.rows("SELECT * FROM gsync_echo")) {
            it.size() == 1
            it[0].message == echo.message
            Assert.timestampIs(it[0].timestamp, echo.timestamp)
        }
    }

    def "save: PKが存在する場合は更新される"() {
        given:
        final entity = Fixture.setup(Faker.fake(EchoEntity))
        final echo = new Echo(
            UUID.fromString(entity.echoId),
            Faker.alphanumeric(),
            LocalDateTime.now(),
        )

        when:
        this.sut.save(Faker.fake(GameContext), echo)

        then:
        with(sql.rows("SELECT * FROM gsync_echo")) {
            it.size() == 1
            it[0].message == echo.message
            Assert.timestampIs(it[0].timestamp, echo.timestamp)
        }
    }

    def "findByID: idから検索できる"() {
        given:
        final entity = new EchoEntity(
            Faker.uuidv5("e1").toString(),
            "hello",
            now,
            now,
            now,
        )
        Fixture.setup(entity)

        when:
        final result = this.sut.findByID(Faker.fake(GameContext), id)

        then:
        result == expected

        where:
        id                 || expected
        Faker.uuidv5("e1") || new Echo(Faker.uuidv5("e1"), "hello", now)
        Faker.uuidv5("e2") || null
    }
}
