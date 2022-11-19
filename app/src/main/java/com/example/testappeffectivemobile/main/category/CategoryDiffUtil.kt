package com.example.testappeffectivemobile.main.category

import androidx.recyclerview.widget.DiffUtil


class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryModel>() {
    override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
        return (oldItem.name == newItem.name)
    }
}