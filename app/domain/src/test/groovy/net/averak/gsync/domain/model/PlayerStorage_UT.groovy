package net.averak.gsync.domain.model

import net.averak.gsync.core.exception.ErrorCode
import net.averak.gsync.core.exception.GsyncException
import net.averak.gsync.testkit.AbstractSpec
import net.averak.gsync.testkit.Assert
import net.averak.gsync.testkit.Faker

class PlayerStorage_UT extends AbstractSpec {

    def "ofFirstRevision: 初期リビジョンを作成できる"() {
        when:
        final result = PlayerStorage.ofFirstRevision(
            Faker.uuidv4(),
            Faker.uuidv4(),
        )

        then:
        result.revision == UUID.fromString("00000000-0000-0000-0000-000000000000")
        result.entries == []
    }

    def "validate: リビジョンが一致するか検証する"() {
        given:
        final sut = Faker.fake(PlayerStorage, [
            "revision": Faker.uuidv5("r1"),
        ])

        when:
        sut.validate(Faker.uuidv5("r1"))

        then:
        noExceptionThrown()

        when:
        sut.validate(Faker.uuidv5("r2"))

        then:
        final ex = thrown(GsyncException)
        Assert.exceptionIs(ex, new GsyncException(ErrorCode.PLAYER_STORAGE_REVISION_MISMATCH))
    }

    def "set: エントリをセットする"() {
        given:
        final playerStorage = Faker.fake(PlayerStorage, [
            "revision": Faker.uuidv5("r1"),
            "entries" : [
                Faker.fake(PlayerStorageEntry, [key: "key1", value: "value1".bytes]),
                Faker.fake(PlayerStorageEntry, [key: "key2", value: "value2".bytes]),
            ],
        ])
        final entries = [
            Faker.fake(PlayerStorageEntry, [key: "key2", value: "new value2".bytes]),
            Faker.fake(PlayerStorageEntry, [key: "key3", value: "new value3".bytes]),
        ]

        when:
        playerStorage.set(*entries)

        then:
        playerStorage.entries*.key == ["key1", "key2", "key3"]
        playerStorage.entries*.value == ["value1".bytes, "new value2".bytes, "new value3".bytes]
        playerStorage.revision != Faker.uuidv5("r1")
    }

    def "clearAll: 全エントリを消去する"() {
        given:
        final playerStorage = Faker.fake(PlayerStorage, [
            "revision": Faker.uuidv5("r1"),
            "entries" : [
                Faker.fake(PlayerStorageEntry, [key: "key1", value: "value1".bytes]),
                Faker.fake(PlayerStorageEntry, [key: "key2", value: "value2".bytes]),
            ],
        ])

        when:
        playerStorage.clearAll()

        then:
        playerStorage.entries*.key == ["key1", "key2"]
        playerStorage.entries*.value == [new byte[0], new byte[0]]
        playerStorage.revision != Faker.uuidv5("r1")
    }
}

class PlayerStorageEntry_UT extends AbstractSpec {

    def "constructor: 100 KiB を超過するとエラーになる"() {
        when:
        new PlayerStorageEntry("key", new byte[100 * 1024])

        then:
        noExceptionThrown()

        when:
        new PlayerStorageEntry("key", new byte[100 * 1024 + 1])

        then:
        final ex = thrown(GsyncException)
        Assert.exceptionIs(ex, new GsyncException(ErrorCode.PLAYER_STORAGE_VALUE_EXCESS_MAX_SIZE))
    }

    def "clear: エントリを消去する"() {
        given:
        final entry = Faker.fake(PlayerStorageEntry)

        when:
        entry.clear()

        then:
        entry.value == new byte[0]
    }

    def "isCleared: エントリが消去されているか判定する"() {
        given:
        final entry = Faker.fake(PlayerStorageEntry, [value: value])

        when:
        final result = entry.isCleared()

        then:
        result == expected

        where:
        value       || expected
        new byte[0] || true
        new byte[1] || false
    }

    def "equals: 等価判定"() {
        given:
        final entry = new PlayerStorageEntry(
            "key1",
            "value1".bytes,
        )

        when:
        final result = entry == other

        then:
        result == expected

        where:
        other                                          || expected
        new PlayerStorageEntry("key1", "value1".bytes) || true
        new PlayerStorageEntry("key2", "value1".bytes) || false
        new PlayerStorageEntry("key1", "value2".bytes) || false
        new PlayerStorageEntry("key2", "value2".bytes) || false
        Faker.fake(Object)                             || false
    }

    def "hashCode: ハッシュコードを取得できる"() {
        when:
        final result = entry.hashCode()

        then:
        result == expected
        where:
        entry                                     || expected
        new PlayerStorageEntry("", 1.byteValue()) || (31 * "".hashCode()) + 31 + 1
        new PlayerStorageEntry("", 2.byteValue()) || (31 * "".hashCode()) + 31 + 2
    }
}
