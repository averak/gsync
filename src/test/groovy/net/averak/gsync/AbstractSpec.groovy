package net.averak.gsync

import io.quarkus.arc.All
import io.quarkus.test.junit.QuarkusTest
import jakarta.annotation.PostConstruct
import jakarta.inject.Inject
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.IRandomizer
import spock.lang.Specification

@QuarkusTest
abstract class AbstractSpec extends Specification {

    @Inject
    @All
    List<IRandomizer> randomizers

    @PostConstruct
    void init() {
        Faker.init(randomizers)
    }

    /**
     * 例外を検証
     */
    static void verify(final GsyncException actual, final GsyncException expected) {
        assert actual.errorCode == expected.errorCode
    }

}
