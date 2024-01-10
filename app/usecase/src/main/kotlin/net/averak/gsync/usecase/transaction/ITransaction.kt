package net.averak.gsync.usecase.transaction

interface ITransaction {

    fun <T> roTx(block: () -> T): T

    fun <T> rwTx(block: () -> T): T
}
