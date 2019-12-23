package nl.jovmit.katas.sales

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaleShould {

    @Mock
    private lateinit var catalog: Catalog
    @Mock
    private lateinit var display: Display

    private val barcode = "1234"
    private val price = "$7.15"
    private val productNotFound = null

    private lateinit var sale: Sale

    @Before
    fun initialize() {
        sale = Sale(display, catalog)
    }

    @Test
    fun displayPriceForGivenBarcode() {
        given(catalog.findPrice(barcode)).willReturn(price)

        sale.onBarcode(barcode)

        verify(display).displayPrice(price)
    }

    @Test
    fun displayProductNotFoundErrorMessageForUnknownBarcode() {
        given(catalog.findPrice(barcode)).willReturn(productNotFound)

        sale.onBarcode(barcode)

        verify(display).displayProductNotFoundMessage(barcode)
    }

    @Test
    fun displayEmptyBarcodeMessage() {
        val emptyBarcode = ""

        sale.onBarcode(emptyBarcode)

        verify(display).displayEmptyBarcodeMessage()
    }
}