package com.example.testappeffectivemobile.main

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi
import com.example.testappeffectivemobile.main.hot.HotSalesModel
import com.example.testappeffectivemobile.network.HotAndBestSalesModel

class MainRepository(private var remoteApi: RemoteApi) {

    suspend fun getHotSalesList(url: String): ApiResponse<HotAndBestSalesModel> {
        return try {
            remoteApi = ApiFactory.getApi()
            val result = remoteApi.getHotSales(url)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getHotSalesList", e.toString())
            ApiResponse(null, e)
        }
    }
}