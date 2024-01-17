package net.averak.gsync.testkit.api.grpc

import io.grpc.Status

data class Response<T>(
    val status: Status,
    val message: T?,
)
