package nl.jovmit.katas.banking

import com.nhaarman.mockitokotlin2.given
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionRepositoryShould {

    @Mock
    private lateinit var clock: Clock
    private lateinit var transactionRepository: TransactionRepository

    private val today = "12/05/2015"

    @Before
    fun initialize() {
        transactionRepository = TransactionRepository(clock)
        given(clock.todayAsString()).willReturn(today)
    }

    @Test
    fun create_and_store_a_deposit_transaction() {
        transactionRepository.addDeposit(100)

        val transactions = transactionRepository.allTransactions()

        assertThat(transactions.size, `is`(1))
        assertThat(transactions.first(), `is`(Transaction(today, 100)))
    }

    @Test
    fun create_and_store_a_withdrawal_transaction() {
        transactionRepository.addWithdraw(200)

        val transactions = transactionRepository.allTransactions()

        assertThat(transactions.size, `is`(1))
        assertThat(transactions.first(), `is`(Transaction(today, -200)))
    }
}