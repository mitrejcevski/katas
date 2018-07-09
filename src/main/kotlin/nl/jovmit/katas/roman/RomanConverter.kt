package nl.jovmit.katas.roman

class RomanConverter {

    fun convert(input: String): Int {
        var result = 0
        var index = 0
        val romanNumerals = input.toUpperCase()
        while (index < romanNumerals.length) {
            val currentValue = convertSingleChar(romanNumerals[index])
            if (index + 1 < romanNumerals.length) {
                val nextValue = convertSingleChar(romanNumerals[index + 1])
                if (currentValue >= nextValue) {
                    result += currentValue
                } else {
                    result += (nextValue - currentValue)
                    index++
                }
            } else {
                result += currentValue
                index++
            }
            index++
        }
        return result
    }

    private fun convertSingleChar(input: Char): Int {
        return when (input) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }
    }
}
