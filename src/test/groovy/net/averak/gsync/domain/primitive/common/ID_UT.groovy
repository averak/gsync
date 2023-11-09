package net.averak.gsync.domain.primitive.common

import net.averak.gsync.AbstractSpec
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.testkit.Faker

class ID_UT extends AbstractSpec {

    def "constructor: 正常に作成できる"() {
        when:
        new ID(value)

        then:
        noExceptionThrown()

        where:
        value << [
            Faker.uuidv4(),
            Faker.uuidv5("1"),
        ]
    }

    def "ID: 制約違反の場合は例外を返す"() {
        when:
        new ID(value)

        then:
        final exception = thrown(GsyncException)
        verify(exception, new GsyncException(ErrorCode.ID_FORMAT_IS_INVALID))

        where:
        value << [
            Faker.alphanumeric(36),
            Faker.uuidv4() + "A",
        ]
    }

}