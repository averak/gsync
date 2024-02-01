package net.averak.gsync.adapter.handler.player_api

import io.grpc.Status
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.FriendRequest
import net.averak.gsync.domain.model.Friendship
import net.averak.gsync.schema.protobuf.player_api.FriendListV1
import net.averak.gsync.schema.protobuf.player_api.FriendSendRequestV1
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.api.grpc.Response
import net.averak.gsync.testkit.fixture.builder.master.FriendSettingBuilder
import net.averak.gsync.testkit.fixture.builder.master.MasterDataBuilder
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

class FriendHandler_SendRequestV1_IT extends AbstractDatabaseSpec {

    def "フレンドリクエストを送信する"() {
        given:
        final gctx = Faker.fake(GameContext)
        masterUp.setup(
            gctx,
            new MasterDataBuilder()
                .friendSetting(new FriendSettingBuilder().maxFriendRequestCount(2).build())
                .build(),
        )
        playerUp.setup(
            gctx,
            new PlayerDataBuilder(Faker.uuidv5("p1")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p2")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p3")).build(),
            new PlayerDataBuilder(Faker.uuidv5("p4")).build(),
        )
        testcase.given()

        when:
        final response = grpcTester.invoke(
            grpcTester.friend.&sendRequestV1,
            testcase.when.request,
        ) {
            it.session(Faker.uuidv5("p1"), Faker.uuidv5("g1"))
            it.spoofingMasterVersion(gctx.masterVersion)
        }

        then:
        testcase.then(response)

        where:
        testcase << [
            [
                name : "フレンドリクエスト送信上限に達していない場合、送信できる",
                given: () -> {
                    playerUp.setup(
                        new PlayerDataBuilder(Faker.uuidv5("p1"))
                            .friendRequests(
                                new FriendRequest(Faker.uuidv5("p1"), Faker.uuidv5("p2"), LocalDateTime.now()),
                            )
                            .build(),
                    )
                },
                when : [
                    request: FriendSendRequestV1.Request.newBuilder()
                        .setPlayerId(Faker.uuidv5("p3").toString())
                        .build(),
                ],
                then : (Response<FriendSendRequestV1.Response> v) -> {
                    assert v.status == Status.OK
                    with(sql.rows("SELECT * FROM gsync_player_friend_request")) {
                        assert it.size() == 2
                        assert it*.player_id.sort() == [Faker.uuidv5("p1").toString(), Faker.uuidv5("p1").toString()].sort()
                        assert it*.receiver_player_id.sort() == [Faker.uuidv5("p2").toString(), Faker.uuidv5("p3").toString()].sort()
                    }
                    return true
                },
            ],
            [
                name : "フレンドリクエスト送信上限に達している場合、送信できない",
                given: () -> {
                    playerUp.setup(
                        new PlayerDataBuilder(Faker.uuidv5("p1"))
                            .friendRequests(
                                new FriendRequest(Faker.uuidv5("p1"), Faker.uuidv5("p2"), LocalDateTime.now()),
                                new FriendRequest(Faker.uuidv5("p1"), Faker.uuidv5("p3"), LocalDateTime.now()),
                            )
                            .build(),
                    )
                },
                when : [
                    request: FriendSendRequestV1.Request.newBuilder()
                        .setPlayerId(Faker.uuidv5("p4").toString())
                        .build(),
                ],
                then : (Response<FriendSendRequestV1.Response> v) -> {
                    assert v.status.code == Status.ABORTED.code
                    assert v.status.description == "player sent friend requests is over max count."
                    return true
                },
            ],
        ]
    }

}
