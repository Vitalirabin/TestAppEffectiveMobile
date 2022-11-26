package com.example.testappeffectivemobile.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CartViewModel(private val cartUseCase: CartUseCase) : ViewModel() {
    val cartModel = MutableLiveData<CartModel>()

    fun getCartInfo() {
        viewModelScope.launch {
            cartModel.value = cartUseCase.getCartInfo()
        }
    }
}