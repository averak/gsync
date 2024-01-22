package net.averak.gsync.infrastructure.grpc.player_api.metadata

enum class OutgoingTrailerKey(val key: String) {

    RESPOND_TIMESTAMP("x-respond-timestamp"),
}
