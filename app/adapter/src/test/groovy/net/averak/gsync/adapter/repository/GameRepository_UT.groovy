package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.GameDto
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Game
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired

class GameRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    GameRepository sut

    def "findByIDs: IDリストからゲームリストを取得できる"() {
        given:
        Fixture.setup(
            Faker.fake(GameDto, [gameId: Faker.uuidv5("g1").toString()]),
            Faker.fake(GameDto, [gameId: Faker.uuidv5("g2").toString()]),
        )

        when:
        final actual = this.sut.findByIDs(Faker.fake(GameContext), ids)

        then:
        actual*.id.sort() == expectedIDs.sort()

        where:
        ids                                                          || expectedIDs
        []                                                           || []
        [Faker.uuidv5("g1")]                                         || [Faker.uuidv5("g1")]
        [Faker.uuidv5("g1"), Faker.uuidv5("g2")]                     || [Faker.uuidv5("g1"), Faker.uuidv5("g2")]
        [Faker.uuidv5("g1"), Faker.uuidv5("g2"), Faker.uuidv5("g3")] || [Faker.uuidv5("g1"), Faker.uuidv5("g2")]
    }

    def "save: ゲームを保存できる"() {
        given:
        testcase.given()

        when:
        this.sut.save(Faker.fake(GameContext), testcase.when.game)

        then:
        testcase.then()

        where:
        testcase << [
            [
                name : "レコードが存在する場合、更新される",
                given: () -> {
                    Fixture.setup(
                        Faker.fake(GameDto, [gameId: Faker.uuidv5("g1").toString()]),
                    )
                },
                when : [
                    game: Faker.fake(Game, [id: Faker.uuidv5("g1"), name: "updated"]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_game")) {
                        assert it.size() == 1
                        assert it[0].game_id == Faker.uuidv5("g1").toString()
                        assert it[0].name == "updated"
                    }
                    return true
                },
            ],
            [
                name : "レコードが存在しない場合、作成される",
                given: () -> { },
                when : [
                    game: Faker.fake(Game, [id: Faker.uuidv5("g1"), name: "created"]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_game")) {
                        assert it.size() == 1
                        assert it[0].game_id == Faker.uuidv5("g1").toString()
                        assert it[0].name == "created"
                    }
                    return true
                },
            ],
        ]
    }
}
