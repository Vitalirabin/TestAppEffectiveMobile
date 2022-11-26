package com.example.testappeffectivemobile.di

import com.example.lolstatistic.network.ApiFactory
import com.example.testappeffectivemobile.cart.CartRepository
import com.example.testappeffectivemobile.cart.CartUseCase
import com.example.testappeffectivemobile.cart.CartViewModel
import com.example.testappeffectivemobile.main.MainRepository
import com.example.testappeffectivemobile.main.MainUseCase
import com.example.testappeffectivemobile.main.MainViewModel
import com.example.testappeffectivemobile.product.ProductDetailsRepository
import com.example.testappeffectivemobile.product.ProductDetailsUseCase
import com.example.testappeffectivemobile.product.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainRepoModule = module(override = true) {
    single { ApiFactory.getApi() }
    single { MainRepository(get()) }
}
val mainViewModule = module(override = true) {
    viewModel { MainViewModel(get()) }
}
val mainUseCase = module(override = true) {
    single { MainUseCase(get()) }
}
val productRepoModule = module(override = true) {
    single { ApiFactory.getApi() }
    single { ProductDetailsRepository(get()) }
}
val productViewModule = module(override = true) {
    viewModel { ProductDetailsViewModel(get()) }
}
val productUseCase = module(override = true) {
    single { ProductDetailsUseCase(get()) }
}
val cartRepoModule = module(override = true) {
    single { ApiFactory.getApi() }
    single { CartRepository(get()) }
}
val cartViewModule = module(override = true) {
    viewModel { CartViewModel(get()) }
}
val cartUseCase = module(override = true) {
    single { CartUseCase(get()) }
}