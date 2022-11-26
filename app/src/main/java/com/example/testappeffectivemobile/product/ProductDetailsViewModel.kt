package com.example.testappeffectivemobile.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappeffectivemobile.product.capacity.CapacityItemModel
import com.example.testappeffectivemobile.product.color.ColorItemModel
import kotlinx.coroutines.launch

class ProductDetailsViewModel(val productDetailsUseCase: ProductDetailsUseCase) : ViewModel() {
    val productDetailsModel = MutableLiveData<ProductDetailsModel>()
    val productColorModels = MutableLiveData<MutableList<ColorItemModel>>()
    val productCapacityModels = MutableLiveData<MutableList<CapacityItemModel>>()

    fun loadProductDetails() {
        viewModelScope.launch {
            val listColors = mutableListOf<ColorItemModel>()
            val listCapacity = mutableListOf<CapacityItemModel>()
            productDetailsModel.value =
                productDetailsUseCase.getProductDetails()
            productDetailsUseCase.getProductDetails()?.color?.forEach {
                val color = ColorItemModel(it, false)
                listColors.add(color)
            }
            productDetailsUseCase.getProductDetails()?.capacity?.forEach {
                val capacityItemModel = CapacityItemModel(it, false)
                listCapacity.add(capacityItemModel)
            }
            productColorModels.value = listColors
            productCapacityModels.value = listCapacity
        }
    }

    fun colorClick(color: String) {
        productColorModels.value?.forEach {
            it.isCheck = it.color == color
        }
    }

    fun capacityClick(capacity: String) {
        productCapacityModels.value?.forEach {
            it.isSelect = it.capacity == capacity
        }

    }
}