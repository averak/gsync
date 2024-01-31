package net.averak.gsync.adapter.handler.player_api

import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.FriendRequest
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
            new PlayerDataBuilder(Faker.uuidv5("p1")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p2")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p3")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p4")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p5")).build(),
        )
        playerUp.setup(
            gctx,
            new PlayerDataBuilder(Faker.uuidv5("p1"))
                .friendships(
                    new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p2"), LocalDateTime.now()),
                    new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p3"), LocalDateTime.now()),
                )
                .friendRequests(
                    new FriendRequest(Faker.uuidv5("p1"), Faker.uuidv5("p4"), LocalDateTime.now()),
                )
                .build(),
            new PlayerDataBuilder(Faker.uuidv5("p5"))
                .friendRequests(
                    new FriendRequest(Faker.uuidv5("p5"), Faker.uuidv5("p1"), LocalDateTime.now()),
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
        with(response.message.friendsList) {
            it*.playerId == [Faker.uuidv5("p2").toString(), Faker.uuidv5("p3").toString()]
        }
        with(response.message.sentFriendRequestsList) {
            it*.receiverPlayerId == [Faker.uuidv5("p4").toString()]
        }
        with(response.message.receivedFriendRequestsList) {
            it*.senderPlayerId == [Faker.uuidv5("p5").toString()]
        }
    }
}
