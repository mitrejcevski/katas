package nl.jovmit.katas.sales

class Display(private val console: Console) {

    fun displayPrice(price: String) {
        console.print(price)
    }

    fun displayProductNotFoundMessage(barcode: String) {
        console.print("Product not found for $barcode")
    }

    fun displayEmptyBarcodeMessage() {
        console.print("Scanning error: empty barcode")
    }
}
