package net.averak.gsync.core.exception

enum class ErrorCode(val summary: String) {
    UNKNOWN("Unknown error is thrown."),

    VALID_MASTER_VERSION_DEFINITION_IS_NOT_FOUND("Valid master version definition is not found."),
    MULTIPLE_VALID_MASTER_VERSIONS_ARE_DEFINED("Multiple valid master versions are defined."),
    REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND("Required client version definition is not found."),

    CLIENT_OS_MUST_BE_SPECIFIED("Client OS must be specified."),
    CLIENT_VERSION_MUST_BE_SPECIFIED("Client version must be specified."),
    CLIENT_VERSION_IS_NOT_SUPPORTED("Client version is not supported."),

    // プレイヤーストレージ機能
    PLAYER_STORAGE_VALUE_EXCESS_MAX_SIZE("Player storage value excess max size of 100 KiB."),
    PLAYER_STORAGE_REVISION_MISMATCH("Player storage revision mismatch."),
}
