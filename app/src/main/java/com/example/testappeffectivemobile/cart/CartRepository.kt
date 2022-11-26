package com.example.testappeffectivemobile.cart

import android.util.Log
import com.example.lolstatistic.network.ApiFactory
import com.example.lolstatistic.network.ApiResponse
import com.example.lolstatistic.network.RemoteApi

class CartRepository(private var remoteApi: RemoteApi) {

    suspend fun getCartInfo(url: String): ApiResponse<CartModelWithApi> {
        return try {
            remoteApi = ApiFactory.getApi()
            val result = remoteApi.getCartInfo(url)
            val apiResponse = ApiResponse(result, null)
            apiResponse
        } catch (e: Throwable) {
            Log.e("getHotSalesList", e.toString())
            ApiResponse(null, e)
        }
    }
}