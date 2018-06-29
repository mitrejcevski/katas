package nl.jovmit.katas.password

class PasswordVerifier {

    companion object {
        private const val MIN_PASSWORD_LENGTH_REQUIREMENT = 8
    }

    fun verify(password: String): Boolean {
        return when {
            !password.satisfiesLengthRequirement() ->
                throw IllegalArgumentException("Password too short")
            !password.containsUpperCase() ->
                throw IllegalArgumentException("At least one upper case letter required")
            !password.containsLowerCase() ->
                throw IllegalArgumentException("At least one lower case letter required")
            !password.containsDigit() ->
                throw IllegalArgumentException("At least one digit required")
            else -> true
        }
    }

    private fun String.satisfiesLengthRequirement() =
            this.length >= MIN_PASSWORD_LENGTH_REQUIREMENT

    private fun String.containsUpperCase() =
            this != this.toLowerCase()

    private fun String.containsLowerCase() =
            this != this.toUpperCase()

    private fun String.containsDigit() =
            this.matches(Regex(".*\\d+.*"))
}
