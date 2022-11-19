package com.example.lolstatistic.network

import com.example.testappeffectivemobile.main.hot.HotSalesModel
import com.example.testappeffectivemobile.network.HotAndBestSalesModel
import retrofit2.http.GET
import retrofit2.http.Url

interface RemoteApi {
    @GET
    suspend fun getHotSales(@Url url: String): HotAndBestSalesModel
}