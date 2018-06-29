package nl.jovmit.katas.password

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PasswordVerifierTest {

    private lateinit var verifier: PasswordVerifier

    @Before
    fun setup() {
        verifier = PasswordVerifier()
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowPasswordTooShortException() {
        verifier.verify("short")
    }

    @Test(expected = IllegalArgumentException::class)
    fun passwordShouldHaveAtLeastOneUpperCaseLetter() {
        verifier.verify("lowercase")
    }

    @Test(expected = IllegalArgumentException::class)
    fun passwordShouldHaveAtLeastOneLowerCaseLetter() {
        assertFalse(verifier.verify("UPPERCASE"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun passwordShouldHaveAtLeastOneDigit() {
        assertFalse(verifier.verify("camelCase"))
    }

    @Test
    fun shouldReturnOkWhenAllConditionsMet() {
        assertTrue(verifier.verify("longPass1"))
    }
}
