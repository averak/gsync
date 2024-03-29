package net.averak.gsync.adapter.handler.utils

import net.averak.gsync.adapter.dao.dto.base.MasterVersionDto
import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.fixture.Fixture
import org.springframework.beans.factory.annotation.Autowired

class MasterVersionUtils_UT extends AbstractDatabaseSpec {

    @Autowired
    MasterVersionUtils sut

    def "getValidMasterVersion: 正常系 - 有効なマスターバージョンを取得できる"() {
        given:
        testcase.given()

        when:
        final actual = this.sut.getValidMasterVersion(testcase.when.spoofingMasterVersion)

        then:
        testcase.then(actual)

        where:
        testcase << [
            [
                name : "有効なマスターバージョンが1つだけ存在する場合、正常に取得できる",
                given: () -> {
                    Fixture.setup(
                        Faker.fake(MasterVersionDto, [version: Faker.uuidv5("m1").toString(), isValid: true]),
                        Faker.fake(MasterVersionDto, [version: Faker.uuidv5("m2").toString(), isValid: false]),
                    )
                },
                when : [
                    spoofingMasterVersion: null,
                ],
                then : (UUID v) -> {
                    assert v == Faker.uuidv5("m1")
                    return true
                },
            ],
            [
                name : "spoofingMasterVersion が指定された場合、データベースは参照せずバージョンを返す",
                given: () -> { },
                when : [
                    spoofingMasterVersion: Faker.uuidv5("m2")
                ],
                then : (UUID v) -> {
                    assert v == Faker.uuidv5("m2")
                    return true
                },
            ],
        ]
    }

    def "getValidMasterVersion: 異常系"() {
        given:
        testcase.given()

        when:
        this.sut.getValidMasterVersion(testcase.when.spoofingMasterVersion)

        then:
        final ex = thrown(GsyncException)
        testcase.then(ex)

        where:
        testcase << [
            [
                name : "有効なマスターバージョンが複数存在する場合",
                given: () -> {
                    Fixture.setup(
                        Faker.fake(MasterVersionDto, [version: Faker.uuidv5("m1").toString(), isValid: true]),
                        Faker.fake(MasterVersionDto, [version: Faker.uuidv5("m2").toString(), isValid: true]),
                    )
                },
                when : [
                    spoofingMasterVersion: null,
                ],
                then : (GsyncException v) -> {
                    Assert.exceptionIs(v, new GsyncException(ErrorCode.MULTIPLE_VALID_MASTER_VERSIONS_ARE_DEFINED))
                    return true
                },
            ],
            [
                name : "有効なマスターバージョンが1つも存在しない場合",
                given: () -> {
                    Fixture.setup(
                        Faker.fake(MasterVersionDto, [version: Faker.uuidv5("m1").toString(), isValid: false]),
                        Faker.fake(MasterVersionDto, [version: Faker.uuidv5("m2").toString(), isValid: false]),
                    )
                },
                when : [
                    spoofingMasterVersion: null,
                ],
                then : (GsyncException v) -> {
                    Assert.exceptionIs(v, new GsyncException(ErrorCode.VALID_MASTER_VERSION_DEFINITION_IS_NOT_FOUND))
                    return true
                },
            ],
        ]
    }
}
