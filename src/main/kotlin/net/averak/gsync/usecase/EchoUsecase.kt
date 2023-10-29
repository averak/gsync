package net.averak.gsync.usecase

import jakarta.inject.Singleton
import net.averak.gsync.domain.model.Echo

@Singleton
class EchoUsecase {
    fun echo(message: String): Echo {
        return Echo(message)
    }
}
