package net.averak.gsync.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("gsync")
@Suppress("MemberVisibilityCanBePrivate")
open class Config {

    var version: String = ""

    var debug: Boolean = false

    lateinit var playerApi: PlayerApi

    lateinit var adminApi: AdminApi

    @Configuration
    @ConfigurationProperties("gsync.player-api")
    open class PlayerApi {

        var port: Int = 0
    }

    @Configuration
    @ConfigurationProperties("gsync.admin-api")
    open class AdminApi {

        var port: Int = 0
    }
}
