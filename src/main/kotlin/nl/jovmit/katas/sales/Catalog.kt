package nl.jovmit.katas.sales

class Catalog(private val pricesByBarcode: Map<String, String>) {

    fun findPrice(barcode: String): String? {
        return pricesByBarcode[barcode]
    }
}
