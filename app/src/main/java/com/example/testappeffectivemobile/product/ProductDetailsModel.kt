package com.example.testappeffectivemobile.product

data class ProductDetailsModel(
    val CPU: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val id: String,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: String,
    val rating: String,
    val sd: String,
    val ssd: String,
    val title: String
)
