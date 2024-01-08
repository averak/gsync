package net.averak.gsync.testkit

import net.averak.gsync.Entrypoint
import org.spockframework.spring.EnableSharedInjection
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(classes = [Entrypoint])
@ActiveProfiles("test")
@EnableSharedInjection
abstract class AbstractSpec extends Specification {
}
