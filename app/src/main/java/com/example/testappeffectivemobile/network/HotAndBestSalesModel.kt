package com.example.testappeffectivemobile.network

import com.example.testappeffectivemobile.main.hot.HotSalesModel
import com.google.gson.annotations.SerializedName

data class HotAndBestSalesModel (
    @SerializedName("home_store")
    var hotSales:List<HotSalesModel>,
  //  @SerializedName("best_seller")
   // var bestSallers:List<String>
    )