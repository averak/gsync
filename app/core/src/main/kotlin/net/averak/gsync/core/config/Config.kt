package net.averak.gsync.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("gsync")
@Suppress("MemberVisibilityCanBePrivate")
open class Config {

    var version: String = ""

    var debug: Boolean = false

    lateinit var grpc: Grpc

    @Configuration
    @ConfigurationProperties("gsync.grpc")
    open class Grpc {

        var port: Int = 0

        var enableReflection = false
    }
}
