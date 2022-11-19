package com.example.testappeffectivemobile.main.hot

import androidx.recyclerview.widget.DiffUtil


class HotSalesDiffUtil : DiffUtil.ItemCallback<HotSalesModel>() {
    override fun areItemsTheSame(oldItem: HotSalesModel, newItem: HotSalesModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HotSalesModel, newItem: HotSalesModel): Boolean {
        return (oldItem.id == newItem.id)
    }
}