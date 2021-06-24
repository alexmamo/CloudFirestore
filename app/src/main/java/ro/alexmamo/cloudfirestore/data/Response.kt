package ro.alexmamo.cloudfirestore.data

data class Response(
    var products: List<Product>? = null,
    var exception: Exception? = null
)