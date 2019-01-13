package nl.jovmit.katas.banking

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PrintStatementFeature {

    @Mock
    private lateinit var console: Console
    @Mock
    private lateinit var clock: Clock

    private lateinit var account: Account

    @Before
    fun initialize() {
        val transactionRepository = TransactionRepository(clock)
        val statementPrinter = StatementPrinter(console)
        account = Account(transactionRepository, statementPrinter)
    }

    @Test
    fun printStatementContainingAllTransactions() {
        given(clock.todayAsString()).willReturn("01/04/2014", "02/04/2014", "10/04/2014")

        account.deposit(1000)
        account.withdraw(100)
        account.deposit(500)
        account.printStatement()

        val inOrder = inOrder(console)
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE")
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00")
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00")
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00")
    }
}