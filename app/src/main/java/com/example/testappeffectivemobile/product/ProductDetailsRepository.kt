package com.example.testappeffectivemobile.product

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi
import com.example.testappeffectivemobile.network.HotAndBestSalesModel

class ProductDetailsRepository(private var remoteApi: RemoteApi) {
    suspend fun getProductDetails(url: String): ApiResponse<ProductDetailsModel> {
        return try {
            remoteApi = ApiFactory.getApi()
            val result = remoteApi.getProductDetails(url)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getHotSalesList", e.toString())
            ApiResponse(null, e)
        }
    }
}