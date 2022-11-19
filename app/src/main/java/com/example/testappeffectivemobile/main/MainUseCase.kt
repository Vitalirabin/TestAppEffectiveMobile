package com.example.testappeffectivemobile.main

import com.example.testappeffectivemobile.main.hot.HotSalesModel
import com.example.testappeffectivemobile.network.HotAndBestSalesModel

class MainUseCase(val mainRepository: MainRepository?) {

    suspend fun getHotSalesList(): HotAndBestSalesModel? {
        return mainRepository?.getHotSalesList("https://run.mocky.io/v3/654bd15e-b121-49ba-a588-960956b15175")?.data
    }
}