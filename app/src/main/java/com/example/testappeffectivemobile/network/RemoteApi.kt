package com.example.lolstatistic.network

import com.example.testappeffectivemobile.cart.CartModelWithApi
import com.example.testappeffectivemobile.network.HotAndBestSalesModel
import com.example.testappeffectivemobile.product.ProductDetailsModel
import retrofit2.http.GET
import retrofit2.http.Url

interface RemoteApi {
    @GET
    suspend fun getHotSales(@Url url: String): HotAndBestSalesModel

    @GET
    suspend fun getProductDetails(@Url url: String): ProductDetailsModel

    @GET
    suspend fun getCartInfo(@Url url: String): CartModelWithApi
}