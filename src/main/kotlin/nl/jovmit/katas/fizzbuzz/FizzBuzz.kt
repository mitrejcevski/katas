package nl.jovmit.katas.fizzbuzz

class FizzBuzz {

    fun buildNumbersSequence(input: Int = 0): String {
        return if (input == 0) {
            buildString {
                (1..100).forEach {
                    append(convertNumber(it))
                    append(System.lineSeparator())
                }
            }
        } else {
            convertNumber(input)
        }
    }

    private fun convertNumber(it: Int): String {
        return when {
            it % 3 == 0 && it % 5 == 0 -> "FizzBuzz"
            it % 3 == 0 || containsDigit(it, 3) -> "Fizz"
            it % 5 == 0 || containsDigit(it, 5) -> "Buzz"
            else -> it.toString()
        }
    }

    private fun containsDigit(number: Int, digitToLookFor: Int): Boolean {
        var numberValue = number
        while (numberValue > 0) {
            if (numberValue % 10 == digitToLookFor)
                return true
            numberValue /= 10
        }
        return false
    }
}
