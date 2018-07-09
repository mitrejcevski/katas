package nl.jovmit.katas.roman

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RomanConverterTest {

    private lateinit var converter: RomanConverter

    @Before
    fun setUp() {
        converter = RomanConverter()
    }

    @Test
    fun shouldConvertTheIToOne() {
        assertEquals(1, converter.convert("I"))
    }

    @Test
    fun shouldConvertTheVToFive() {
        assertEquals(5, converter.convert("V"))
    }

    @Test
    fun shouldConvertTheXToTen() {
        assertEquals(10, converter.convert("X"))
    }

    @Test
    fun shouldConvertTheLToFifty() {
        assertEquals(50, converter.convert("L"))
    }

    @Test
    fun shouldConvertTheCToHundred() {
        assertEquals(100, converter.convert("C"))
    }

    @Test
    fun shouldConvertTheDToHundred() {
        assertEquals(500, converter.convert("D"))
    }

    @Test
    fun shouldConvertTheMToThousand() {
        assertEquals(1000, converter.convert("M"))
    }

    @Test
    fun shouldReturnSumForProvidedSameValueInRow() {
        assertEquals(2, converter.convert("II"))
        assertEquals(20, converter.convert("XX"))
    }

    @Test
    fun shouldReturnSumForProvidedSmallerValueAfterLargerValue() {
        assertEquals(6, converter.convert("VI"))
        assertEquals(21, converter.convert("XXI"))
    }

    @Test
    fun shouldReturnSubtractWhenSmallerValuePrecedeBiggerValue() {
        assertEquals(4, converter.convert("IV"))
        assertEquals(19, converter.convert("IXX"))
    }

    @Test
    fun shouldReturnCorrectValueForGivenInput() {
        assertEquals(2006, converter.convert("MMVI"))
        assertEquals(1944, converter.convert("MCMXLIV"))
    }
}