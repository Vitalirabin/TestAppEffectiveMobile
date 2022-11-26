package com.example.testappeffectivemobile.main

import com.example.testappeffectivemobile.network.HotAndBestSalesModel

class MainUseCase(val mainRepository: MainRepository?) {

    suspend fun getHotSalesList(): HotAndBestSalesModel? {
        return mainRepository?.getHotSalesList("https://run.mocky.io/v3/654bd15e-b121-49ba-a588-960956b15175")?.data
    }

    suspend fun getSizeOfCart(): Int? {
        return mainRepository?.getCartInfo("https://run.mocky.io/v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")?.data?.basketList?.size
    }
}