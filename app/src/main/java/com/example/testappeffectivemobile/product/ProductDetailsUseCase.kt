package com.example.testappeffectivemobile.product

class ProductDetailsUseCase(val repository: ProductDetailsRepository) {
    suspend fun getProductDetails():ProductDetailsModel?{
        return repository.getProductDetails("https://run.mocky.io/v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5").data
    }
}