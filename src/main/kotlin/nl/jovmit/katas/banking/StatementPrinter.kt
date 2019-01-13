package nl.jovmit.katas.banking

import java.text.DecimalFormat
import java.util.concurrent.atomic.AtomicInteger

open class StatementPrinter(private val console: Console) {

    private companion object {
        private const val STATEMENT_HEADER = "DATE | AMOUNT | BALANCE"
        private val decimalFormatter = DecimalFormat("#.00")
    }

    open fun print(transactions: List<Transaction>) {
        console.printLine(STATEMENT_HEADER)
        printStatementLines(transactions)
    }

    private fun printStatementLines(transactions: List<Transaction>) {
        val runningBalance = AtomicInteger(0)
        transactions.map { statementLine(it, runningBalance) }
                .asReversed()
                .forEach(console::printLine)
    }

    private fun statementLine(transaction: Transaction,
                              runningBalance: AtomicInteger): String {

        return with(transaction) {
            val formattedRunningBalance = decimalFormatter.format(runningBalance.addAndGet(amount))
            "$date | ${decimalFormatter.format(amount)} | $formattedRunningBalance"
        }
    }
}
