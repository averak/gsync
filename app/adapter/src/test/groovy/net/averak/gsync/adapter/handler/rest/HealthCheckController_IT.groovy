package net.averak.gsync.adapter.handler.rest

import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import org.springframework.http.HttpStatus

import java.time.LocalDateTime

class HealthCheckController_IT extends AbstractDatabaseSpec {

    // API PATH
    static final String BASE_PATH = "/api/health"
    static final String HEALTH_CHECK_PATH = BASE_PATH

    def "ヘルスチェックAPI: 正常系 200 OKを返す"() {
        given:
        final now = LocalDateTime.now()

        when:
        final response = this.restTester.get(HEALTH_CHECK_PATH)
            .spoofingMasterVersion(Faker.uuidv5("active"))
            .spoofingCurrentTime(now)
            .execute(HealthCheckController.Response)

        then:
        response.status == HttpStatus.OK
        response.body.message == "Health Check"
        Assert.timestampIs(response.body.timestamp, now)

        then:
        with(sql.rows("SELECT * FROM gsync_echo")) {
            it.size() == 1
            it[0].message == "Health Check"
            Assert.timestampIs(it[0].timestamp, now)
        }
    }
}
