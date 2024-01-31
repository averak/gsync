package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.Friendship
import net.averak.gsync.schema.protobuf.player_api.FriendListV1
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.fixture.builder.player.PlayerDataBuilder

import java.time.LocalDateTime

class FriendHandler_ListV1_IT extends AbstractDatabaseSpec {

    def "正常系"() {
        given:
        final gctx = Faker.fake(GameContext)
        playerUp.setup(
            gctx,
            new PlayerDataBuilder(Faker.uuidv5("p2")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p3")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p4")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p1"))
                .friendships(
                    new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p2"), LocalDateTime.now()),
                    new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p3"), LocalDateTime.now()),
                )
                .build(),
        )

        when:
        final response = grpcTester.invoke(
            grpcTester.friend.&listV1,
            FriendListV1.Request.newBuilder().build(),
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(gctx.masterVersion)
        }

        then:
        response.message.friendsList*.playerId == [Faker.uuidv5("p2").toString(), Faker.uuidv5("p3").toString()]
    }
}
