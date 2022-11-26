package com.example.testappeffectivemobile.cart

import androidx.recyclerview.widget.DiffUtil

class CartDiffUtil : DiffUtil.ItemCallback<BasketModel>() {
    override fun areItemsTheSame(oldItem: BasketModel, newItem: BasketModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BasketModel, newItem: BasketModel): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.images == newItem.images && oldItem.count == newItem.count
    }
}