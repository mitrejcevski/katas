package nl.jovmit.katas.fizzbuzz

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FizzBuzzTest {

    private lateinit var fizzBuzz: FizzBuzz

    @Before
    fun setup() {
        fizzBuzz = FizzBuzz()
    }

    @Test
    fun shouldPrintFizzForNumbersDividableBy3InsteadOfNumber() {
        assertEquals("Fizz", fizzBuzz.buildNumbersSequence(3))
        assertEquals("Fizz", fizzBuzz.buildNumbersSequence(6))
    }

    @Test
    fun shouldPrintBuzzForNumbersDividableBy5InsteadOfNumber() {
        assertEquals("Buzz", fizzBuzz.buildNumbersSequence(5))
        assertEquals("Buzz", fizzBuzz.buildNumbersSequence(10))
    }

    @Test
    fun shouldPrintFizzBuzzForNumbersDividableByBoth3and5() {
        assertEquals("FizzBuzz", fizzBuzz.buildNumbersSequence(15))
        assertEquals("FizzBuzz", fizzBuzz.buildNumbersSequence(30))
    }

    @Test
    fun shouldPrintFizzForNumbersContainingDigit3() {
        assertEquals("Fizz", fizzBuzz.buildNumbersSequence(13))
        assertEquals("14", fizzBuzz.buildNumbersSequence(14))
    }

    @Test
    fun shouldPrintBuzzForNumbersContainingDigit5() {
        assertEquals("Buzz", fizzBuzz.buildNumbersSequence(25))
        assertEquals("16", fizzBuzz.buildNumbersSequence(16))
    }
}