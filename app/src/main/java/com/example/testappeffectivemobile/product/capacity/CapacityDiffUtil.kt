package com.example.testappeffectivemobile.product.capacity

import androidx.recyclerview.widget.DiffUtil


class CapacityDiffUtil : DiffUtil.ItemCallback<CapacityItemModel>() {
    override fun areItemsTheSame(oldItem: CapacityItemModel, newItem: CapacityItemModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: CapacityItemModel,
        newItem: CapacityItemModel
    ): Boolean {
        return (oldItem == newItem && oldItem.isSelect == newItem.isSelect)
    }
}