package nl.jovmit.katas.sales

import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DisplayShould {

    @Mock
    private lateinit var console: Console

    private lateinit var display: Display

    @Before
    fun initialize() {
        display = Display(console)
    }

    @Test
    fun printPrice() {
        val price = "$7.15"

        display.displayPrice(price)

        verify(console).print(price)
    }

    @Test
    fun printProductNotFound() {
        val barcode = "12345"

        display.displayProductNotFoundMessage(barcode)

        verify(console).print("Product not found for $barcode")
    }

    @Test
    fun printEmptyBarcodeMessage() {
        display.displayEmptyBarcodeMessage()

        verify(console).print("Scanning error: empty barcode")
    }
}