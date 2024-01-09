package net.averak.gsync.usecase.transaction

interface ITransaction {

    fun <T> beginRoTransaction(block: () -> T): T

    fun <T> beginRwTransaction(block: () -> T): T
}
