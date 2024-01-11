package net.averak.gsync.core.exception

enum class ErrorCode(val summary: String) {
    UNKNOWN("Unknown error is thrown."),

    MULTIPLE_ENABLED_MASTER_VERSIONS_ARE_DEFINED("Multiple enabled master versions are defined."),
    ENABLED_MASTER_VERSION_IS_NOT_DEFINED("Enabled master version is not defined."),
}
