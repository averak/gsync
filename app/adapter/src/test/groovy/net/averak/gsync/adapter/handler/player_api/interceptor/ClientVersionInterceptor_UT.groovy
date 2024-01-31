package net.averak.gsync.adapter.handler.player_api.interceptor

import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionDto
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.domain.model.Os
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.fixture.Fixture
import org.springframework.beans.factory.annotation.Autowired

class ClientVersionInterceptor_UT extends AbstractDatabaseSpec {

    @Autowired
    ClientVersionInterceptor sut

    def "不正なクライアントバージョンの場合はエラーを返す"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.1.0", os: Os.IOS.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.2.0", os: Os.ANDROID.id.toLong()]),
        )

        when:

        final response = grpcTester.invoke(
            grpcTester.echo.&echoV1,
            EchoEchoV1.Request.newBuilder().setMessage("").build(),
        ) {
            it.spoofingMasterVersion(Faker.uuidv4())
            it.client(clientVersion, os)
        }

        then:
        !response.status.isOk()

        where:
        clientVersion | os
        "1.0.0"       | Os.IOS
        "1.1.0"       | Os.ANDROID
    }

    def "verifyClientVersion: 正常系 - データが存在する場合は正しく判定できる"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.0.0", os: Os.IOS.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.1.0", os: Os.ANDROID.id.toLong()]),
        )

        when:
        final actual = this.sut.verifyClientVersion(masterVersion, clientVersion, os)

        then:
        actual == expected

        where:
        masterVersion          | clientVersion | os         || expected
        Faker.uuidv5("active") | "1.0.0"       | Os.IOS     || true
        Faker.uuidv5("active") | "0.0.9"       | Os.IOS     || false
        Faker.uuidv5("active") | "1.1.0"       | Os.ANDROID || true
        Faker.uuidv5("active") | "1.0.9"       | Os.ANDROID || false
        Faker.uuidv5("active") | null          | null       || true
    }

    def "verifyClientVersion: 異常系"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), os: Os.IOS.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), os: Os.ANDROID.id.toLong()]),
        )

        when:
        this.sut.verifyClientVersion(masterVersion, clientVersion, os)

        then:
        final actual = thrown(GsyncException)
        Assert.exceptionIs(actual, expected)

        where:
        masterVersion            | clientVersion | os         || expected
        Faker.uuidv5("active")   | "1.0.0"       | null       || new GsyncException(ErrorCode.CLIENT_OS_MUST_BE_SPECIFIED)
        Faker.uuidv5("active")   | null          | Os.IOS     || new GsyncException(ErrorCode.CLIENT_VERSION_MUST_BE_SPECIFIED)
        Faker.uuidv5("outdated") | "1.0.0"       | Os.IOS     || new GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
        Faker.uuidv5("outdated") | "1.0.0"       | Os.ANDROID || new GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
    }
}
