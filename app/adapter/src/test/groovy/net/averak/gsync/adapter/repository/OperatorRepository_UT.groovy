package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.OperatorDto
import net.averak.gsync.adapter.dao.dto.base.RGameOperatorDto
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
            Faker.fake(RGameOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                gameId    : Faker.uuidv5("g1").toString(),
            ]),
            Faker.fake(RGameOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                gameId    : Faker.uuidv5("g2").toString(),
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
                    assert v.authorities*.gameID.sort() == [Faker.uuidv5("g1"), Faker.uuidv5("g2")].sort()
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

    def "findByGameID: ゲームIDから取得できる"() {
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
            Faker.fake(RGameOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                gameId    : Faker.uuidv5("g1").toString(),
            ]),
            Faker.fake(RGameOperatorDto, [
                operatorId: Faker.uuidv5("o1").toString(),
                gameId    : Faker.uuidv5("g2").toString(),
            ]),
            Faker.fake(RGameOperatorDto, [
                operatorId: Faker.uuidv5("o2").toString(),
                gameId    : Faker.uuidv5("g1").toString(),
            ]),
        )

        when:
        final actual = this.sut.findByGameID(Faker.fake(GameContext), gameID)

        then:
        actual*.id.sort() == expectedIDs.sort()

        where:
        gameID             || expectedIDs
        Faker.uuidv5("g1") || [Faker.uuidv5("o1"), Faker.uuidv5("o2")]
        Faker.uuidv5("g2") || [Faker.uuidv5("o1")]
    }
}
