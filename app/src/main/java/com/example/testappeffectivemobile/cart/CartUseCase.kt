package com.example.testappeffectivemobile.cart

class CartUseCase(private val cartRepository: CartRepository) {

    suspend fun getCartInfo(): CartModel? {
        val cartModelWithApi =
            cartRepository.getCartInfo("https://run.mocky.io/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149").data
        val listBasket = mutableListOf<BasketModel>()
        cartRepository.getCartInfo("https://run.mocky.io/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149").data
            ?.basketList?.forEach {
                val basketModel = BasketModel(it.id, it.images, it.price, it.title, 1)
                listBasket.add(basketModel)
            }
        var cartModel = CartModel(
            listBasket.toList(),
            cartModelWithApi?.delivery ?: "",
            cartModelWithApi?.id ?: "",
            cartModelWithApi?.total ?: ""
        )
        return cartModel
    }
}