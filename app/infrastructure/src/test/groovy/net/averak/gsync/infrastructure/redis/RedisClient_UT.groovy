package net.averak.gsync.infrastructure.redis

import net.averak.gsync.testkit.AbstractDatabaseSpec
import org.springframework.beans.factory.annotation.Autowired

class RedisClient_UT extends AbstractDatabaseSpec {

    @Autowired
    RedisClient sut

    def "get: キーの値を取得する"() {
        given:
        this.sut.set("k1", "v1")

        when:
        final result = this.sut.get(key)

        then:
        result == expectedResult

        where:
        key  || expectedResult
        "k1" || "v1"
        "k2" || null
    }

    def "exists: キーの存在を確認する"() {
        given:
        this.redis.set("k1", "v1")

        when:
        final result = this.sut.exists(key)

        then:
        result == expectedResult

        where:
        key  || expectedResult
        "k1" || true
        "k2" || false
    }

    def "ttl: キーの有効期限を取得する"() {
        given:
        this.redis.setex("k1", "v1", 10)

        when:
        final result = this.sut.ttl(key)

        then:
        result == expectedResult

        where:
        key  || expectedResult
        "k1" || 10
        "k2" || -2
    }

    def "set: キーの値を設定する"() {
        given:
        this.redis.set("k1", "v1")

        when:
        this.sut.set(key, value)

        then:
        this.redis.get(key) == expectedValue

        where:
        key  | value     || expectedResult | expectedValue
        "k1" | "updated" || true           | "updated"
        "k2" | "created" || true           | "created"
    }

    def "setnx: キーが存在しない場合に、キーの値をセットする"() {
        given:
        this.redis.set("k1", "v1")

        when:
        final result = this.sut.setnx(key, value)

        then:
        result == expectedResult
        this.redis.get(key) == expectedValue

        where:
        key  | value     || expectedResult || expectedValue
        "k1" | "updated" || false          || "v1"
        "k2" | "created" || true           || "created"
    }

    def "setex: キーの値をセットすると同時に、キーの有効期限を設定する"() {
        given:
        this.redis.set("k1", "v1")

        when:
        this.sut.setex(key, value, seconds)

        then:
        this.redis.get(key) == expectedValue
        this.redis.ttl(key) == expectedTtl

        where:
        key  | value     | seconds || expectedValue || expectedTtl
        "k1" | "updated" | 10      || "updated"     || 10
        "k2" | "created" | 20      || "created"     || 20
    }

    def "del: キーを削除する"() {
        given:
        this.redis.set("k1", "v1")
        this.redis.set("k2", "v2")

        when:
        final result = this.sut.del(keys)

        then:
        result == expectedResult

        where:
        keys               || expectedResult
        []                 || 0
        ["k1"]             || 1
        ["k1", "k2"]       || 2
        ["k1", "k2", "k3"] || 2
    }

    def "expire: キーの有効期限を設定する"() {
        given:
        this.redis.set("k1", "v1")

        when:
        final result = this.sut.expire(key, seconds)

        then:
        result == expectedResult
        this.redis.ttl(key) == expectedTtl

        where:
        key  | seconds || expectedResult || expectedTtl
        "k1" | 10      || true           || 10
        "k2" | 20      || false          || -2
    }

    def "incr: キーの値をインクリメントする"() {
        given:
        this.redis.set("k1", "1")

        when:
        final result = this.sut.incr(key)

        then:
        result == expectedResult
        this.redis.get(key) == expectedValue

        where:
        key  || expectedResult || expectedValue
        "k1" || 2              || "2"
        "k2" || 1              || "1"
    }

    def "decr: キーの値をデクリメントする"() {
        given:
        this.redis.set("k1", "2")

        when:
        final result = this.sut.decr(key)

        then:
        result == expectedResult
        this.redis.get(key) == expectedValue

        where:
        key  || expectedResult || expectedValue
        "k1" || 1              || "1"
        "k2" || -1             || "-1"
    }

    def "flushdb: データベース内の全てのキーを削除する"() {
        given:
        this.redis.set("k1", "v1")
        this.redis.set("k2", "v2")

        when:
        this.sut.flushdb()

        then:
        this.redis.get("k1") == null
        this.redis.get("k2") == null
    }

    def "transaction: トランザクション内で処理を実行する"() {
        when:
        final result = this.sut.transaction {
            this.sut.set("k1", "1")
            this.sut.incr("k1")
            this.sut.decr("k1")
        }

        then:
        result == [true, 2, 1]
        this.redis.get("k1") == "1"
    }
}
