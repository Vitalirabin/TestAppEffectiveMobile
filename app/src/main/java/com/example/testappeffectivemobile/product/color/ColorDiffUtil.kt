package com.example.testappeffectivemobile.product.color

import androidx.recyclerview.widget.DiffUtil


class ColorDiffUtil : DiffUtil.ItemCallback<ColorItemModel>() {
    override fun areItemsTheSame(oldItem: ColorItemModel, newItem: ColorItemModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ColorItemModel, newItem: ColorItemModel): Boolean {
        return (oldItem.color == newItem.color && oldItem.isCheck == newItem.isCheck)
    }
}