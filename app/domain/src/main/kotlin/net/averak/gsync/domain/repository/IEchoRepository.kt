package net.averak.gsync.domain.repository

import net.averak.gsync.domain.model.Echo

fun interface IEchoRepository {

    fun save(echo: Echo)
}
