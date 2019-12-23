package nl.jovmit.katas.sales

class Sale(private val display: Display,
           private val catalog: Catalog) {

    fun onBarcode(barcode: String) {
        if (barcode.isBlank()) {
            display.displayEmptyBarcodeMessage()
            return
        }
        val price = catalog.findPrice(barcode)
        if (price == null) {
            display.displayProductNotFoundMessage(barcode)
        } else {
            display.displayPrice(price)
        }
    }
}
