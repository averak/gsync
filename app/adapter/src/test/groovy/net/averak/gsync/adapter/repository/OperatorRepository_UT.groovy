package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.OperatorDto
import net.averak.gsync.adapter.dao.dto.base.RTenantOperatorDto
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Operator
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired

class OperatorRepository_UT extends AbstractRepository_UT {

    @Autowired
    OperatorRepository sut

    def "findByID: IDからオペレーターを取得できる"() {
        given:
        Fixture.setup(Faker.fake(OperatorDto, [
            operatorId: Faker.uuidv5("o1").toString(),
        ]))
        Fixture.setup(
            Faker.fake(RTenantOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                tenantId  : Faker.uuidv5("t1").toString(),
            ]),
            Faker.fake(RTenantOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                tenantId  : Faker.uuidv5("t2").toString(),
            ]),
        )

        when:
        final actual = this.sut.findByID(Faker.fake(GameContext), testcase.when.id)

        then:
        testcase.then(actual)

        where:
        testcase << [
            [
                name: "存在する場合",
                when: [
                    id: Faker.uuidv5("o1"),
                ],
                then: (Operator v) -> {
                    assert v != null
                    assert v.id == Faker.uuidv5("o1")
                    assert v.authorities*.tenantID.sort() == [Faker.uuidv5("t1"), Faker.uuidv5("t2")].sort()
                    return true
                },
            ],
            [
                name: "存在しない場合",
                when: [
                    id: Faker.uuidv5("o2"),
                ],
                then: (Operator v) -> {
                    assert v == null
                    return true
                },
            ],
        ]
    }

    def "findByTenantID: テナントIDから取得できる"() {
        given:
        Fixture.setup(
            Faker.fake(OperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
            ]),
            Faker.fake(OperatorDto, [
                operatorId: Faker.uuidv5("o2").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(RTenantOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                tenantId  : Faker.uuidv5("t1").toString(),
            ]),
            Faker.fake(RTenantOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                tenantId  : Faker.uuidv5("t2").toString(),
            ]),
            Faker.fake(RTenantOperatorDto, [
                operatorId: Faker.uuidv5("o2").toString(),
                tenantId  : Faker.uuidv5("t1").toString(),
            ]),
        )

        when:
        final actual = this.sut.findByTenantID(Faker.fake(GameContext), tenantID)

        then:
        actual*.id.sort() == expectedIDs.sort()

        where:
        tenantID           || expectedIDs
        Faker.uuidv5("t1") || [Faker.uuidv5("o1"), Faker.uuidv5("o2")]
        Faker.uuidv5("t2") || [Faker.uuidv5("o1")]
    }
}
