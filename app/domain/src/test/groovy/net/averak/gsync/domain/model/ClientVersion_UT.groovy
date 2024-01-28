package net.averak.gsync.domain.model

import net.averak.gsync.testkit.AbstractSpec
import net.averak.gsync.testkit.Faker

class ClientVersion_UT extends AbstractSpec {

    def "greaterThan: 指定したバージョンが要求バージョン未満であることを判定"() {
        given:
        final clientVersion = new ClientVersion(
            new RequiredClientVersionSetting(
                Semver.parse("1.1.1"),
                Faker.url(),
            ),
            new RequiredClientVersionSetting(
                Semver.parse("2.2.2"),
                Faker.url(),
            )
        )

        when:
        final result = clientVersion.greaterThan(os, version)

        then:
        result == expected

        where:
        os         | version               | expected
        Os.IOS     | Semver.parse("1.1.1") | false
        Os.IOS     | Semver.parse("0.1.1") | true
        Os.IOS     | Semver.parse("1.0.1") | true
        Os.IOS     | Semver.parse("1.1.0") | true
        Os.ANDROID | Semver.parse("2.2.2") | false
        Os.ANDROID | Semver.parse("1.2.2") | true
        Os.ANDROID | Semver.parse("2.1.2") | true
        Os.ANDROID | Semver.parse("2.2.1") | true
    }

    def "getDownloadUrl: ダウンロードURLを取得する"() {
        given:
        final clientVersion = new ClientVersion(
            new RequiredClientVersionSetting(
                Semver.parse("0.0.0"),
                "https://store/apple/0.0.0",
            ),
            new RequiredClientVersionSetting(
                Semver.parse("0.0.0"),
                "https://store/google/0.0.0",
            )
        )

        when:
        final result = clientVersion.getDownloadUrl(os)

        then:
        result == expected

        where:
        os         | expected
        Os.IOS     | "https://store/apple/0.0.0"
        Os.ANDROID | "https://store/google/0.0.0"
    }
}
