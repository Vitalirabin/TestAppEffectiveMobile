package com.example.testappeffectivemobile.main.best

data class BeastSellerModel(
    val id: String,
    val is_favorites: Boolean,
    val title: String,
    val price_without_discount: String,
    val discount_price: String,
    val picture: String
)