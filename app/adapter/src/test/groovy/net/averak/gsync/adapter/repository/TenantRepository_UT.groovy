package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.TenantDto
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Tenant
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.Fixture
import org.springframework.beans.factory.annotation.Autowired

class TenantRepository_UT extends AbstractRepository_UT {

    @Autowired
    TenantRepository sut

    def "findByIDs: IDリストからテナントIDリストを取得できる"() {
        given:
        Fixture.setup(
            Faker.fake(TenantDto, [tenantId: Faker.uuidv5("t1").toString()]),
            Faker.fake(TenantDto, [tenantId: Faker.uuidv5("t2").toString()]),
        )

        when:
        final actual = this.sut.findByIDs(Faker.fake(GameContext), ids)

        then:
        actual*.id.sort() == expectedIDs.sort()

        where:
        ids                                                          || expectedIDs
        []                                                           || []
        [Faker.uuidv5("t1")]                                         || [Faker.uuidv5("t1")]
        [Faker.uuidv5("t1"), Faker.uuidv5("t2")]                     || [Faker.uuidv5("t1"), Faker.uuidv5("t2")]
        [Faker.uuidv5("t1"), Faker.uuidv5("t2"), Faker.uuidv5("t3")] || [Faker.uuidv5("t1"), Faker.uuidv5("t2")]
    }

    def "save: テナントを保存できる"() {
        given:
        testcase.given()

        when:
        this.sut.save(Faker.fake(GameContext), testcase.when.tenant)

        then:
        testcase.then()

        where:
        testcase << [
            [
                name : "レコードが存在する場合、更新される",
                given: () -> {
                    Fixture.setup(
                        Faker.fake(TenantDto, [tenantId: Faker.uuidv5("t1").toString()]),
                    )
                },
                when : [
                    tenant: Faker.fake(Tenant, [id: Faker.uuidv5("t1"), name: "updated"]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_tenant")) {
                        assert it.size() == 1
                        assert it[0].tenant_id == Faker.uuidv5("t1").toString()
                        assert it[0].name == "updated"
                    }
                    return true
                },
            ],
            [
                name : "レコードが存在しない場合、作成される",
                given: () -> { },
                when : [
                    tenant: Faker.fake(Tenant, [id: Faker.uuidv5("t1"), name: "created"]),
                ],
                then : () -> {
                    with(sql.rows("SELECT * FROM gsync_tenant")) {
                        assert it.size() == 1
                        assert it[0].tenant_id == Faker.uuidv5("t1").toString()
                        assert it[0].name == "created"
                    }
                    return true
                },
            ],
        ]
    }
}
