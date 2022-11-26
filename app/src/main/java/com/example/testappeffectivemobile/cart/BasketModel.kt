package com.example.testappeffectivemobile.cart

data class BasketModel(
    val id: String,
    val images: String,
    val price: String,
    val title: String,
    var count: Int = 1
)
