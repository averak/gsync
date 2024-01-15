package net.averak.gsync.adapter.handler.player_api.scope

enum class IncomingMD(val key: String) {

    CLIENT_VERSION("x-client-version"),
    PLATFORM("x-platform"),
    IDEMPOTENCY_KEY("x-idempotency-key"),
    GAME_ID("x-game-id"),

    // 以下はデバッグモードの場合のみ有効になる
    DEBUG_SPOOFING_PLAYER_ID("x-debug-spoofing-player-id"),
    DEBUG_SPOOFING_MASTER_VERSION("x-debug-spoofing-master-version"),
    DEBUG_SPOOFING_CURRENT_TIME("x-debug-spoofing-current-time"),
}
