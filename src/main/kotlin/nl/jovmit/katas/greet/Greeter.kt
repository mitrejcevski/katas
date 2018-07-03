package nl.jovmit.katas.greet

class Greeter {

    fun greet(vararg names: String): String {
        val parsed = parseInput(names)
        return when (parsed.size) {
            0 -> "Hello, my friend."
            1 -> greetingForSingleInput(parsed)
            2 -> "Hello, ${names.first()} and ${names.last()}."
            else -> makeComplexGreeting(parsed.toTypedArray())
        }
    }

    private fun greetingForSingleInput(parsed: List<String>): String {
        val name = parsed.last()
        return if (name.toUpperCase() == name) "HELLO $name!"
        else "Hello, ${parsed.last()}."
    }

    private fun makeComplexGreeting(input: Array<out String>): String {
        val defaultNames = input.filter { it.toUpperCase() != it }.toTypedArray()
        val shoutedNames = input.filter { it.toUpperCase() == it }
        return buildString {
            appendGreetingForDefaultNames(defaultNames)
            if (shoutedNames.isNotEmpty()) {
                appendGreetingForShoutedNames(shoutedNames)
            }
        }
    }

    private fun StringBuilder.appendGreetingForShoutedNames(shoutedNames: List<String>) {
        append(" AND HELLO ")
        if (shoutedNames.size > 1) {
            for (index in 0 until shoutedNames.size - 1) {
                if (index > 0) {
                    append(",")
                }
                append(" ${shoutedNames[index]}")
            }
            append(" AND ${shoutedNames.last()}!")
        } else {
            append("${shoutedNames.last()}!")
        }
    }

    private fun StringBuilder.appendGreetingForDefaultNames(defaultNames: Array<String>) {
        if (defaultNames.size == 2) {
            append(greet(*defaultNames))
        } else {
            append("Hello, ")
            for (index in 0 until defaultNames.size - 1) {
                append("${defaultNames[index]}, ")
            }
            append("and ${defaultNames.last()}.")
        }
    }

    private fun parseInput(names: Array<out String>) =
            names.flatMap { it.split(",") }
                    .map { it.trim() }
                    .filter { it.isNotBlank() }
}
