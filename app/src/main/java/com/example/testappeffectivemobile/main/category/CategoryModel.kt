package com.example.testappeffectivemobile.main.category

import android.graphics.drawable.Drawable

data class CategoryModel(
    var name: String,
    var icon: Drawable?,
    var selectIcon: Drawable?,
    var background: Drawable?,
    var selectBackground: Drawable?,
    var isSelected: Boolean
)