package com.example.testappeffectivemobile.di

import com.example.lolstatistic.network.ApiFactory
import com.example.testappeffectivemobile.main.MainRepository
import com.example.testappeffectivemobile.main.MainUseCase
import com.example.testappeffectivemobile.main.MainViewModel
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
