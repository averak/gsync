package net.averak.gsync.usecase

import net.averak.gsync.domain.repository.IEchoRepository
import net.averak.gsync.testkit.AbstractSpec
import org.spockframework.spring.SpringBean

abstract class AbstractUsecase_UT extends AbstractSpec {

    @SpringBean
    IEchoRepository echoRepository = Mock()
}
