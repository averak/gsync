package net.averak.gsync.domain.model

import kotlin.Pair
import net.averak.gsync.testkit.AbstractSpec
import net.averak.gsync.testkit.Faker

import java.time.LocalDateTime

class FriendRequest_UT extends AbstractSpec {

    def "approved: リクエストを承認する"() {
        given:
        final now = LocalDateTime.now()
        def friendRequest = Faker.fake(FriendRequest)

        when:
        final result = friendRequest.approved(now)

        then:
        result.playerIDs == new Pair(friendRequest.playerID, friendRequest.receiverPlayerID)
        result.becomeFriendAt == now
    }
}

class Friendship_UT extends AbstractSpec {

    def "constructor: 同じプレイヤーIDの関係は作成不可"() {
        when:
        final now = LocalDateTime.now()
        // noinspection GroovyResultOfObjectAllocationIgnored
        new Friendship(Faker.uuidv5("p1"), Faker.uuidv5("p1"), now)

        then:
        final ex = thrown(IllegalArgumentException)
        ex.message == "playerIDs must be different."
    }
}
