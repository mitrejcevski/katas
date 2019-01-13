package nl.jovmit.katas.banking

open class TransactionRepository(private val clock: Clock) {

    private val transactions = ArrayList<Transaction>()

    open fun addDeposit(amount: Int) {
        val deposit = Transaction(clock.todayAsString(), amount)
        transactions.add(deposit)
    }

    open fun addWithdraw(amount: Int) {
        val withdraw = Transaction(clock.todayAsString(), -amount)
        transactions.add(withdraw)
    }

    open fun allTransactions(): List<Transaction> {
        return transactions.toList()
    }
}
