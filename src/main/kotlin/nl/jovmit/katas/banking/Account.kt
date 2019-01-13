package nl.jovmit.katas.banking


class Account(private val transactionRepository: TransactionRepository,
              private val statementPrinter: StatementPrinter) {

    fun deposit(amount: Int) {
        transactionRepository.addDeposit(amount)
    }

    fun withdraw(amount: Int) {
        transactionRepository.addWithdraw(amount)
    }

    fun printStatement() {
        statementPrinter.print(transactionRepository.allTransactions())
    }
}
