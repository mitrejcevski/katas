package nl.jovmit.katas.string

class Calculator {

    fun calculate(input: String): Int {
        val numbers = input.split(",", "\n")
        return if (input.isBlank()) 0 else calculateSum(numbers)
    }

    private fun calculateSum(numbers: List<String>): Int {
        val values = numbers.map { it.trim().toInt() }
        requireNoNegativeNumbers(values)
        return values.filter { it < 1000 }.sum()
    }

    private fun requireNoNegativeNumbers(values: List<Int>) {
        values.forEach {
            if (it.isNegative) {
                throw IllegalArgumentException("No negative numbers allowed!")
            }
        }
    }

    private val Int.isNegative: Boolean
        get() = this < 0
}
