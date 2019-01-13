package nl.jovmit.katas.banking

import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StatementPrinterShould {

    @Mock
    private lateinit var console: Console

    private val noTransactions: List<Transaction> = emptyList()
    private lateinit var statementPrinter: StatementPrinter

    @Before
    fun initialize() {
        statementPrinter = StatementPrinter(console)
    }

    @Test
    fun always_print_the_header() {
        statementPrinter.print(noTransactions)

        verify(console).printLine("DATE | AMOUNT | BALANCE")
    }

    @Test
    fun print_transactions_in_reverse_chronological_order() {
        val transactions = transactionsContaining(
                deposit("01/04/2014", 1000),
                withdraw("02/04/2014", 100),
                deposit("10/04/2014", 500)
        )
        statementPrinter.print(transactions)

        val inOrder = inOrder(console)
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE")
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00")
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00")
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00")
    }

    private fun transactionsContaining(vararg transactions: Transaction): List<Transaction> {
        return transactions.toList()
    }

    private fun withdraw(date: String, amount: Int): Transaction {
        return Transaction(date, -amount)
    }

    private fun deposit(date: String, amount: Int): Transaction {
        return Transaction(date, amount)
    }
}