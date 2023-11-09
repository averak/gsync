package net.averak.gsync.usecase

import jakarta.inject.Inject
import net.averak.gsync.testkit.Faker

class EchoUsecase_Echo_UT extends AbstractUsecase_UT {

    @Inject
    EchoUsecase sut

    def "echo: 正常系 Echoを作成できる"() {
        given:
        final message = Faker.alphanumeric()

        when:
        final result = sut.echo(message)

        then:
        result.message == message
    }
}
