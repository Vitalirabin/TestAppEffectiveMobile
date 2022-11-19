package com.example.testappeffectivemobile.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.main.category.Category
import com.example.testappeffectivemobile.main.category.CategoryModel

class MainViewModel : ViewModel() {
    val categoryList = mutableListOf<CategoryModel>()

    fun updateCategory(name: String) {
        categoryList.forEach {
            it.isSelected = it.name == name
        }
    }

    fun getCategory(context: Context, select: String): MutableList<CategoryModel> {
        categoryList.add(
            CategoryModel(
                context.getString(Category.CATEGORY_PHONE.nameCategoryResId),
                context.getDrawable(R.drawable.phone),
                context.getDrawable(R.drawable.phone_select),
                context.getDrawable(R.drawable.item_select_category),
                context.getDrawable(R.drawable.item_select_category_select),
                select == "Phone"
            )
        )
        categoryList.add(
            CategoryModel(
                context.getString(Category.CATEGORY_COMPUTER.nameCategoryResId),
                context.getDrawable(R.drawable.computer),
                context.getDrawable(R.drawable.computer_select),
                context.getDrawable(R.drawable.item_select_category),
                context.getDrawable(R.drawable.item_select_category_select),
                select == "Computer"
            )
        )
        categoryList.add(
            CategoryModel(
                context.getString(Category.CATEGORY_HEALTS.nameCategoryResId),
                context.getDrawable(R.drawable.health),
                context.getDrawable(R.drawable.health_select),
                context.getDrawable(R.drawable.item_select_category),
                context.getDrawable(R.drawable.item_select_category_select),
                select == "Health"
            )
        )
        categoryList.add(
            CategoryModel(
                context.getString(Category.CATEGORY_BOOKS.nameCategoryResId),
                context.getDrawable(R.drawable.books),
                context.getDrawable(R.drawable.books_select),
                context.getDrawable(R.drawable.item_select_category),
                context.getDrawable(R.drawable.item_select_category_select),
                select == "Books"
            )
        )
        return categoryList
    }
}