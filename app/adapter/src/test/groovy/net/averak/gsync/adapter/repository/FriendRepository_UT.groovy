package net.averak.gsync.adapter.repository

import net.averak.gsync.adapter.dao.dto.base.*
import net.averak.gsync.core.game_context.GameContext
import net.averak.gsync.domain.model.FriendRequest
import net.averak.gsync.domain.model.FriendSetting
import net.averak.gsync.domain.model.FriendStatus
import net.averak.gsync.domain.model.Friendship
import net.averak.gsync.domain.repository.exception.MasterDataNotFoundException
import net.averak.gsync.testkit.AbstractDatabaseSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker
import net.averak.gsync.testkit.fixture.Fixture
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class FriendRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    FriendRepository sut

    def "getFriends: フレンドリストを取得"() {
        given:
        Fixture.setup(
            // p1 friends -> [p2, p3]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            // p2 friends -> [p1]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            // p3 friends -> [p1]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
            // p4 friends -> []
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p4").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p1").toString(),
                friendPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p1").toString(),
                friendPlayerId: Faker.uuidv5("p3").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p2").toString(),
                friendPlayerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p3").toString(),
                friendPlayerId: Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p4").toString(),
            ]),
        )

        when:
        final result = sut.getFriends(Faker.fake(GameContext), playerID)

        then:
        result*.friendPlayerID == expectedFriendPlayerIDs

        where:
        playerID           || expectedFriendPlayerIDs
        Faker.uuidv5("p1") || [Faker.uuidv5("p2"), Faker.uuidv5("p3")]
        Faker.uuidv5("p2") || [Faker.uuidv5("p1")]
        Faker.uuidv5("p3") || [Faker.uuidv5("p1")]
        Faker.uuidv5("p4") || []
    }

    def "getFriends: プロフィールが存在しない場合はエラーを返す"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p1").toString(),
                friendPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p2").toString(),
                friendPlayerId: Faker.uuidv5("p1").toString(),
            ]),
        )

        when:
        sut.getFriends(Faker.fake(GameContext), Faker.uuidv5("p1"))

        then:
        final ex = thrown(RuntimeException)
        ex.message == "Player profile not found, playerID=${Faker.uuidv5("p2")}."
    }

    def "getFriendStatus: フレンドステータスを取得"() {
        given:
        Fixture.setup(
            // p1 friends -> [p2]
            // p1 sent requests -> [p3]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            // p2 friends -> [p1]
            // p2 sent requests -> []
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            // p3 friends -> []
            // p3 sent requests -> []
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
            // p4 friends -> []
            // p4 sent requests -> []
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p4").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p1").toString(),
                friendPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p2").toString(),
                friendPlayerId: Faker.uuidv5("p1").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p3").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
            Faker.fake(PlayerProfileDto, [
                playerId: Faker.uuidv5("p4").toString(),
            ]),
        )

        when:
        // noinspection GroovyAssignabilityCheck
        final result = sut.getFriendStatus(Faker.fake(GameContext), playerID, *friendPlayerIDs)

        then:
        result == expected

        where:
        playerID           | friendPlayerIDs                                              || expected
        Faker.uuidv5("p1") | [Faker.uuidv5("p2"), Faker.uuidv5("p3"), Faker.uuidv5("p4")] || [FriendStatus.FRIEND, FriendStatus.SENT_REQUEST, FriendStatus.NONE]
        Faker.uuidv5("p2") | [Faker.uuidv5("p1"), Faker.uuidv5("p3"), Faker.uuidv5("p4")] || [FriendStatus.FRIEND, FriendStatus.NONE, FriendStatus.NONE]
        Faker.uuidv5("p3") | [Faker.uuidv5("p1"), Faker.uuidv5("p2"), Faker.uuidv5("p4")] || [FriendStatus.RECEIVED_REQUEST, FriendStatus.NONE, FriendStatus.NONE]
        Faker.uuidv5("p4") | [Faker.uuidv5("p1"), Faker.uuidv5("p2"), Faker.uuidv5("p3")] || [FriendStatus.NONE, FriendStatus.NONE, FriendStatus.NONE]
        Faker.uuidv5("p1") | []                                                           || []
        Faker.uuidv5("p1") | [Faker.uuidv5("p2")]                                         || [FriendStatus.FRIEND]
    }

    def "getSentFriendRequests: 送信したフレンドリクエストリストを取得"() {
        given:
        Fixture.setup(
            // p1 sent requests -> [p2, p3]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            // p2 sent requests -> [p3]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            // p3 sent requests -> []
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p3").toString(),
            ]),
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p2").toString(),
                receiverPlayerId: Faker.uuidv5("p3").toString(),
            ]),
        )

        when:
        final result = sut.getSentFriendRequests(Faker.fake(GameContext), playerID)

        then:
        result*.receiverPlayerID.sort() == expectedReceiverPlayerIDs.sort()

        where:
        playerID           || expectedReceiverPlayerIDs
        Faker.uuidv5("p1") || [Faker.uuidv5("p2"), Faker.uuidv5("p3")]
        Faker.uuidv5("p2") || [Faker.uuidv5("p3")]
        Faker.uuidv5("p3") || []
    }

    def "getReceivedFriendRequests: 受け取ったフレンドリクエストリストを取得"() {
        given:
        Fixture.setup(
            // p1 sent requests -> [p2, p3]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            // p2 sent requests -> [p3]
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            // p3 sent requests -> []
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p3").toString(),
            ]),
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p2").toString(),
                receiverPlayerId: Faker.uuidv5("p3").toString(),
            ]),
        )

        when:
        final result = sut.getReceivedFriendRequests(Faker.fake(GameContext), playerID)

        then:
        result*.playerID.sort() == expectedPlayerIDs.sort()

        where:
        playerID           || expectedPlayerIDs
        Faker.uuidv5("p1") || []
        Faker.uuidv5("p2") || [Faker.uuidv5("p1")]
        Faker.uuidv5("p3") || [Faker.uuidv5("p1"), Faker.uuidv5("p2")]
    }

    def "saveFriendship: フレンド関係を作成"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
        )
        Fixture.setup(
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p2").toString(),
                receiverPlayerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerFriendRequestDto, [
                playerId        : Faker.uuidv5("p1").toString(),
                receiverPlayerId: Faker.uuidv5("p3").toString(),
            ]),
        )

        when:
        final friendship = new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p2"), LocalDateTime.now())
        sut.saveFriendship(Faker.fake(GameContext), friendship)

        then:
        with(sql.rows("SELECT * FROM gsync_player_friend")) {
            it.size() == 2
            it[0].player_id == Faker.uuidv5("p1").toString()
            it[0].friend_player_id == Faker.uuidv5("p2").toString()
            it[1].player_id == Faker.uuidv5("p2").toString()
            it[1].friend_player_id == Faker.uuidv5("p1").toString()
        }
        with(sql.rows("SELECT * FROM gsync_player_friend_request")) {
            it.size() == 1
            it[0].player_id == Faker.uuidv5("p1").toString()
            it[0].receiver_player_id == Faker.uuidv5("p3").toString()
        }
    }

    def "saveFriendRequest: フレンドリクエストを取得"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
        )
        final now = LocalDateTime.now()

        when:
        final friendRequest = new FriendRequest(Faker.uuidv5("p1"), Faker.uuidv5("p2"), now)
        sut.saveFriendRequest(Faker.fake(GameContext), friendRequest)

        then:
        with(sql.rows("SELECT * FROM gsync_player_friend_request")) {
            it.size() == 1
            it[0].player_id == Faker.uuidv5("p1").toString()
            it[0].receiver_player_id == Faker.uuidv5("p2").toString()
            Assert.timestampIs(it[0].sent_at, now)
        }
    }

    def "deleteFriendship: フレンド関係を削除"() {
        given:
        Fixture.setup(
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p1").toString(),
            ]),
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerDto, [
                playerId: Faker.uuidv5("p3").toString(),
            ]),
        )
        Fixture.setup(
            // 以下2レコードが削除されるはず
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p1").toString(),
                friendPlayerId: Faker.uuidv5("p2").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p2").toString(),
                friendPlayerId: Faker.uuidv5("p1").toString(),
            ]),

            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p1").toString(),
                friendPlayerId: Faker.uuidv5("p3").toString(),
            ]),
            Faker.fake(PlayerFriendDto, [
                playerId      : Faker.uuidv5("p3").toString(),
                friendPlayerId: Faker.uuidv5("p1").toString(),
            ]),
        )

        when:
        final friendship = new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p2"), LocalDateTime.now())
        sut.deleteFriendship(Faker.fake(GameContext), friendship)

        then:
        with(sql.rows("SELECT * FROM gsync_player_friend")) {
            it.size() == 2
            it[0].player_id == Faker.uuidv5("p1").toString()
            it[0].friend_player_id == Faker.uuidv5("p3").toString()
            it[1].player_id == Faker.uuidv5("p3").toString()
            it[1].friend_player_id == Faker.uuidv5("p1").toString()
        }
    }

    def "getSetting: フレンド設定マスタを取得"() {
        given:
        Fixture.setup(
            Faker.fake(FriendSettingMasterDto, [
                masterVersion        : Faker.uuidv5("m1").toString(),
                maxFriendCount       : 100.toLong(),
                maxFriendRequestCount: 200.toLong(),
            ]),
        )

        when:
        final result = sut.getSetting(Faker.fake(GameContext, [masterVersion: Faker.uuidv5("m1")]))

        then:
        result == new FriendSetting(100, 200)

        when:
        sut.getSetting(Faker.fake(GameContext, [masterVersion: Faker.uuidv5("m2")]))

        then:
        thrown(MasterDataNotFoundException)
    }
}
