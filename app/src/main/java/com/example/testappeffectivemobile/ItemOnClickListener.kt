package com.example.testappeffectivemobile

import com.example.testappeffectivemobile.cart.BasketModel


interface ItemOnClickListener {
    fun onClick(name: String)
    fun onClickPlus(name: BasketModel)
    fun onClickMinus(name: BasketModel)
}