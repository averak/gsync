package net.averak.gsync.core.exception

enum class ErrorCode(val summary: String) {
    // 400 Bad Request
    VALIDATION_ERROR("Request validation exception was thrown."),
    INVALID_REQUEST_PARAMETERS("Request parameters is invalid."),
    ID_FORMAT_IS_INVALID("ID format is invalid."),

    // 404 Not Found
    NOT_FOUND_API("API not found."),
}
