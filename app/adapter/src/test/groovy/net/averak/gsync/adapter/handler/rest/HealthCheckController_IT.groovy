package net.averak.gsync.adapter.handler.rest

import net.averak.gsync.testkit.Assert
import org.springframework.http.HttpStatus

class HealthCheckController_IT extends AbstractController_IT {

    // API PATH
    static final String BASE_PATH = "/api/health"
    static final String HEALTH_CHECK_PATH = BASE_PATH

    def "ヘルスチェックAPI: 正常系 200 OKを返す"() {
        when:
        final request = this.getRequest(HEALTH_CHECK_PATH)
        this.execute(request, HttpStatus.OK)

        then:
        with(sql.rows("SELECT * FROM gsync_echo")) {
            it.size() == 1
            it[0].message == "Health Check"
            Assert.timestampIs(it[0].timestamp, this.gctx.currentTime)
        }
    }
}
