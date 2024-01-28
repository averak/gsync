package net.averak.gsync.domain.model

import net.averak.gsync.testkit.AbstractSpec

class Semver_UT extends AbstractSpec {

    def "lessThan: 指定したバージョンより小さいことを判定"() {
        given:
        final semver = Semver.parse("1.1.1")

        when:
        final result = semver.lessThan(version)

        then:
        result == expected

        where:
        version               | expected
        Semver.parse("1.1.1") | false
        Semver.parse("0.1.1") | false
        Semver.parse("1.0.1") | false
        Semver.parse("1.1.0") | false
        Semver.parse("2.1.1") | true
        Semver.parse("1.2.1") | true
        Semver.parse("1.1.2") | true
    }

    def "greaterThan: 指定したバージョンより大きいことを判定"() {
        given:
        final semver = Semver.parse("1.1.1")

        when:
        final result = semver.greaterThan(version)

        then:
        result == expected

        where:
        version               | expected
        Semver.parse("1.1.1") | false
        Semver.parse("2.1.1") | false
        Semver.parse("1.2.1") | false
        Semver.parse("1.1.2") | false
        Semver.parse("0.1.1") | true
        Semver.parse("1.0.1") | true
        Semver.parse("1.1.0") | true
    }

    def "parse: バージョン文字列からインスタンス作成する"() {
        given:
        final semver = Semver.parse(version)

        when:
        final result = semver

        then:
        result == expected

        where:
        version | expected
        "0.0.0" | new Semver(0, 0, 0)
        "1.1.1" | new Semver(1, 1, 1)
    }

    def "parse: 不正なフォーマットの場合はエラーを返す"() {
        when:
        Semver.parse(version)

        then:
        thrown(IllegalArgumentException)

        where:
        version << [
            "",
            "0",
            "0.0",
            "0.0.0-alpha",
        ]
    }

    def "toString: バージョン文字列を返す"() {
        when:
        final result = semver.toString()

        then:
        result == expected

        where:
        semver             || expected
        new Semver(0, 0, 0) | "0.0.0"
        new Semver(1, 1, 1) | "1.1.1"
    }
}
