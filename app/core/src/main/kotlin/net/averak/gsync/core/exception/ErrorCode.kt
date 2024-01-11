package net.averak.gsync.core.exception

enum class ErrorCode(val summary: String) {
    UNKNOWN("Unknown error is thrown."),

    ENABLED_MASTER_VERSION_DEFINITION_IS_NOT_FOUND("Enabled master version definition is not found."),
    MULTIPLE_ENABLED_MASTER_VERSIONS_ARE_DEFINED("Multiple enabled master versions are defined."),
    REQUIRED_CLIENT_VERSION_DEFINITION_IS_NOT_FOUND("Required client version definition is not found."),

    PLATFORM_MUST_BE_SPECIFIED("Platform must be specified."),
    CLIENT_VERSION_MUST_BE_SPECIFIED("Client version must be specified."),
    CLIENT_VERSION_IS_NOT_SUPPORTED("Client version is not supported."),
}
