package net.averak.gsync.adapter.handler.rest

import net.averak.gsync.testkit.Faker
import org.springframework.http.HttpStatus

class GlobalRestControllerAdvice_IT extends AbstractController_IT {

    def "異常系 存在しないパスの場合はエラーを返す"() {
        given:
        final path = "/api/xxx"

        when:
        final response = this.httpTester.get(path)
            .spoofingMasterVersion(Faker.uuidv5("active"))
            .execute()

        then:
        response.status == HttpStatus.NOT_FOUND
    }
}
