package net.averak.gsync.domain.model

/**
 * アプリ配信ストア
 */
enum class Platform(val id: Int) {

    APPLE(0),
    GOOGLE(1),
}

enum class Os(val id: Int, val platform: Platform) {

    IOS(0, Platform.APPLE),
    ANDROID(1, Platform.GOOGLE),
}
