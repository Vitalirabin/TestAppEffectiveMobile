package com.example.testappeffectivemobile.main.best

import androidx.recyclerview.widget.DiffUtil
import com.example.testappeffectivemobile.main.hot.HotSalesModel

class BeastSellerDiffUtils: DiffUtil.ItemCallback<BeastSellerModel>() {
    override fun areItemsTheSame(oldItem: BeastSellerModel, newItem: BeastSellerModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BeastSellerModel, newItem: BeastSellerModel): Boolean {
        return (oldItem.id == newItem.id)
    }
}