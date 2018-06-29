package nl.jovmit.katas.string

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCalculate {

    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun emptyStringShouldReturnsZero() {
        assertEquals(0, calculator.calculate(""))
    }

    @Test
    fun singleNumberShouldReturnTheValue() {
        assertEquals(2, calculator.calculate("2"))
    }

    @Test
    fun twoComaDelimitedNumbersShouldReturnSum() {
        assertEquals(4, calculator.calculate("2,2"))
    }

    @Test
    fun twoEnterDelimitedNumbersShouldReturnSum() {
        assertEquals(4, calculator.calculate("2\n2"))
    }

    @Test
    fun threeNumbersDelimitedEitherWayShouldReturnSum() {
        assertEquals(6, calculator.calculate("1,2,3"))
    }

    @Test
    fun spacesInNumbersShouldNotNotMakeTroubles() {
        assertEquals(6, calculator.calculate("1 , 2, 3"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeInputThrowsException() {
        calculator.calculate("-1,2")
    }

    @Test
    fun shouldIgnoreNumbersGreaterThenThousand() {
        assertEquals(20, calculator.calculate("15,5,1000"))
    }
}