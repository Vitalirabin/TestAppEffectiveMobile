package com.example.testappeffectivemobile.cart

import com.google.gson.annotations.SerializedName

data class CartModelWithApi(
    @SerializedName("basket")
    val basketList: List<BasketModelWithApi>,
    val delivery: String,
    val id: String,
    var total: String
)
