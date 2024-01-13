package net.averak.gsync.adapter.transaction

import net.averak.gsync.usecase.transaction.ITransaction
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@Component
class SpannerTransaction(
    private val transactionManager: PlatformTransactionManager,
) : ITransaction {

    override fun <T> roTx(block: () -> T): T {
        val tx = TransactionTemplate(transactionManager)
        tx.isReadOnly = true

        return tx.execute {
            block()
        }!!
    }

    override fun <T> rwTx(block: () -> T): T {
        val tx = TransactionTemplate(transactionManager)
        tx.isReadOnly = false

        return tx.execute {
            block()
        }!!
    }
}
