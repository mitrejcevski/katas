package nl.jovmit.katas.banking

fun main(args: Array<String>) {

    val clock = Clock()
    val transactionRepository = TransactionRepository(clock)
    val console = Console()
    val statementPrinter = StatementPrinter(console)
    val account = Account(transactionRepository, statementPrinter)

    account.deposit(1000)
    account.withdraw(400)
    account.deposit(100)
    account.printStatement()
}