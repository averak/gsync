package net.averak.gsync.adapter.handler.player_api.interceptor

import net.averak.gsync.adapter.dao.dto.base.RequiredClientVersionDto
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.domain.model.Platform
import net.averak.gsync.schema.protobuf.player_api.EchoEchoV1
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired

class ClientVersionInterceptor_UT extends AbstractDatabaseSpec {

    @Autowired
    ClientVersionInterceptor sut

    def "不正なクライアントバージョンの場合はエラーを返す"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.1.0", platform: Platform.APPLE.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.2.0", platform: Platform.GOOGLE.id.toLong()]),
        )

        when:

        final response = grpcTester.invoke(
            grpcTester.echo.&echoV1,
            EchoEchoV1.Request.newBuilder().setMessage("").build(),
        ) {
            it.spoofingMasterVersion(Faker.uuidv4())
            it.client(clientVersion, platform)
        }

        then:
        !response.status.isOk()

        where:
        clientVersion | platform
        "1.0.0"       | Platform.APPLE
        "1.1.0"       | Platform.GOOGLE
    }

    def "verifyClientVersion: 正常系 - データが存在する場合は正しく判定できる"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.0.0", platform: Platform.APPLE.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), clientVersion: "1.1.0", platform: Platform.GOOGLE.id.toLong()]),
        )

        when:
        final actual = this.sut.verifyClientVersion(masterVersion, clientVersion, platform)

        then:
        actual == expected

        where:
        masterVersion          | clientVersion | platform        || expected
        Faker.uuidv5("active") | "1.0.0"       | Platform.APPLE  || true
        Faker.uuidv5("active") | "0.0.9"       | Platform.APPLE  || false
        Faker.uuidv5("active") | "1.1.0"       | Platform.GOOGLE || true
        Faker.uuidv5("active") | "1.0.9"       | Platform.GOOGLE || false
        Faker.uuidv5("active") | null          | null            || true
    }

    def "verifyClientVersion: 異常系"() {
        given:
        Fixture.setup(
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), platform: Platform.APPLE.id.toLong()]),
            Faker.fake(RequiredClientVersionDto, [masterVersion: Faker.uuidv5("active").toString(), platform: Platform.GOOGLE.id.toLong()]),
        )

        when:
        this.sut.verifyClientVersion(masterVersion, clientVersion, platform)

        then:
        final actual = thrown(GsyncException)
        Assert.exceptionIs(actual, expected)

        where:
        masterVersion            | clientVersion | platform        || expected
        Faker.uuidv5("active")   | "1.0.0"       | null            || new GsyncException(ErrorCode.PLATFORM_MUST_BE_SPECIFIED)
        Faker.uuidv5("active")   | null          | Platform.APPLE  || new GsyncException(ErrorCode.CLIENT_VERSION_MUST_BE_SPECIFIED)
        Faker.uuidv5("outdated") | "1.0.0"       | Platform.APPLE  || new GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
        Faker.uuidv5("outdated") | "1.0.0"       | Platform.GOOGLE || new GsyncException(ErrorCode.REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND)
    }
}
