package net.averak.gsync.usecase

import net.averak.gsync.core.gamecontext.GameContext
import net.averak.gsync.testkit.Faker
import org.springframework.beans.factory.annotation.Autowired

class EchoUsecase_UT extends AbstractUsecase_UT {

    @Autowired
    EchoUsecase sut

    def "echo: 正常系 Echoを作成できる"() {
        given:
        final gctx = Faker.fake(GameContext)
        final message = Faker.alphanumeric()

        when:
        final result = this.sut.echo(gctx, message)

        then:
        result.timestamp == gctx.currentTime
        result.message == message
    }
}