package net.averak.gsync.adapter.handler.admin_api.interceptor

import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionDto
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.domain.model.Platform
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class ClientVersionInterceptor_UT extends AbstractDatabaseSpec {

    @Autowired
    ClientVersionInterceptor sut

    def "不正なクライアントバージョンの場合はエラーを返す"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.1.0", platform: Platform.IOS.id.toLong()]),
        )

        when:
        final response = this.restTester.get("/api/health")
            .spoofingMasterVersion(Faker.uuidv5("active"))
            .clientVersion("1.0.0")
            .platform(Platform.IOS)
            .invoke()

        then:
        response.status == HttpStatus.BAD_REQUEST
        response.error.code == ErrorCode.CLIENT_VERSION_IS_NOT_SUPPORTED.name()
    }

    def "verifyClientVersion: 正常系 - データが存在する場合は正しく判定できる"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.0.0", platform: Platform.IOS.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.1.0", platform: Platform.ANDROID.id.toLong()]),
        )

        when:
        final actual = this.sut.verifyClientVersion(masterVersion, clientVersion, platform)

        then:
        actual == expected

        where:
        masterVersion          | clientVersion | platform         || expected
        Faker.uuidv5("active") | "1.0.0"       | Platform.IOS     || true
        Faker.uuidv5("active") | "0.0.9"       | Platform.IOS     || false
        Faker.uuidv5("active") | "1.1.0"       | Platform.ANDROID || true
        Faker.uuidv5("active") | "1.0.9"       | Platform.ANDROID || false
        Faker.uuidv5("active") | "0.999.999"   | Platform.IOS     || false
        Faker.uuidv5("active") | null          | null             || true
    }

    def "verifyClientVersion: 異常系"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), platform: Platform.IOS.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), platform: Platform.ANDROID.id.toLong()]),
        )

        when:
        this.sut.verifyClientVersion(masterVersion, clientVersion, platform)

        then:
        final actual = thrown(GsyncException)
        Assert.exceptionIs(actual, expected)

        where:
        masterVersion            | clientVersion | platform         || expected
        Faker.uuidv5("active")   | "1.0.0"       | null             || new GsyncException(ErrorCode.PLATFORM_MUST_BE_SPECIFIED)
        Faker.uuidv5("active")   | null          | Platform.IOS     || new GsyncException(ErrorCode.CLIENT_VERSION_MUST_BE_SPECIFIED)
        Faker.uuidv5("outdated") | "1.0.0"       | Platform.IOS     || new GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
        Faker.uuidv5("outdated") | "1.0.0"       | Platform.ANDROID || new GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
    }
}
