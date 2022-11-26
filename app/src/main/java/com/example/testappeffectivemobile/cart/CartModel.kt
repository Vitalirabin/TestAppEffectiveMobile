package com.example.testappeffectivemobile.cart

data class CartModel(
    var basketList: List<BasketModel>,
    var delivery: String,
    var id: String,
    var total: String
)
