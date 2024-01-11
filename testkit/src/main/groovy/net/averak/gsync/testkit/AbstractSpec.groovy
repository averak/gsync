package net.averak.gsync.testkit

import net.averak.gsync.Entrypoint
import net.averak.gsync.testkit.api.rest.RestTester
import org.spockframework.spring.EnableSharedInjection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(classes = [Entrypoint])
@ActiveProfiles("test")
@EnableSharedInjection
abstract class AbstractSpec extends Specification {

    @Autowired
    @Shared
    RestTester restTester

}
