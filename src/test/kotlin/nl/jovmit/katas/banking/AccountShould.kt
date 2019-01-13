package nl.jovmit.katas.banking

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AccountShould {

    @Mock
    private lateinit var transactionRepository: TransactionRepository
    @Mock
    private lateinit var statementPrinter: StatementPrinter

    private lateinit var account: Account

    @Before
    fun initialize() {
        account = Account(transactionRepository, statementPrinter)
    }

    @Test
    fun store_a_deposit_transaction() {
        account.deposit(100)

        verify(transactionRepository).addDeposit(100)
    }

    @Test
    fun store_a_withdraw_transaction() {
        account.withdraw(100)

        verify(transactionRepository).addWithdraw(100)
    }

    @Test
    fun print_a_statement() {
        val transactions: List<Transaction> = listOf(Transaction("12/05/2015", 100))
        given(transactionRepository.allTransactions()).willReturn(transactions)

        account.printStatement()

        verify(statementPrinter).print(transactions)
    }
}