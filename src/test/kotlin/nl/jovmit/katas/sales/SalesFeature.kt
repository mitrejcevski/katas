package nl.jovmit.katas.sales

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SalesFeature {

    @Mock
    private lateinit var console: Console

    private val pricesByBarcode = mapOf("12345" to "$7.25", "23456" to "$12.50")

    private lateinit var sale: Sale

    @Before
    fun initialize() {
        val display = Display(console)
        val catalog = Catalog(pricesByBarcode)
        sale = Sale(display, catalog)
    }

    @Test
    fun shouldDisplayPricesForScannedBarcode() {
        sale.onBarcode("12345")
        sale.onBarcode("23456")
        sale.onBarcode("99999")
        sale.onBarcode("")

        val inOrder = inOrder(console)
        inOrder.verify(console).print("$7.25")
        inOrder.verify(console).print("$12.50")
        inOrder.verify(console).print("Product not found for 99999")
        inOrder.verify(console).print("Scanning error: empty barcode")
    }
}