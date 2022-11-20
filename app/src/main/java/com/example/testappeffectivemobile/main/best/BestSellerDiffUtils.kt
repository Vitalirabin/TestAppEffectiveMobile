package com.example.testappeffectivemobile.main.best

import androidx.recyclerview.widget.DiffUtil

class BestSellerDiffUtils: DiffUtil.ItemCallback<BestSellerModel>() {
    override fun areItemsTheSame(oldItem: BestSellerModel, newItem: BestSellerModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BestSellerModel, newItem: BestSellerModel): Boolean {
        return (oldItem.id == newItem.id)
    }
}