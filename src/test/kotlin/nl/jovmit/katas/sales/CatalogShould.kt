package nl.jovmit.katas.sales

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CatalogShould {

    private val pricesByBarcode = mapOf("12345" to "$7.25", "23456" to "$12.50")

    private lateinit var catalog: Catalog

    @Before
    fun initialize() {
        catalog = Catalog(pricesByBarcode)
    }

    @Test
    fun findPriceForGivenBarcode() {
        assertEquals("$7.25", catalog.findPrice("12345"))
    }
}