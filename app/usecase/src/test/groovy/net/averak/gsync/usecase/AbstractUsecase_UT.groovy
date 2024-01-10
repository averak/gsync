package net.averak.gsync.usecase

import kotlin.jvm.functions.Function0
import net.averak.gsync.domain.repository.IEchoRepository
import net.averak.gsync.testkit.AbstractSpec
import net.averak.gsync.usecase.transaction.ITransaction
import org.jetbrains.annotations.NotNull
import org.spockframework.spring.SpringBean

abstract class AbstractUsecase_UT extends AbstractSpec {

    @SpringBean
    IEchoRepository echoRepository = Mock()

    @SuppressWarnings('unused')
    @SpringBean
    ITransaction transaction = new Transaction()

    private class Transaction implements ITransaction {

        @Override
        <T> T roTx(@NotNull Function0<? extends T> block) {
            return block()
        }

        @Override
        <T> T rwTx(@NotNull Function0<? extends T> block) {
            return block()
        }
    }
}
