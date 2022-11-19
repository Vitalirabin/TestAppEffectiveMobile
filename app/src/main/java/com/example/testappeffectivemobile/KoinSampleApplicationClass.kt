package com.example.testappeffectivemobile

import android.app.Application
import com.example.testappeffectivemobile.di.mainRepoModule
import com.example.testappeffectivemobile.di.mainUseCase
import com.example.testappeffectivemobile.di.mainViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class KoinSampleApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinSampleApplicationClass)
            modules(
                listOf(
                    mainRepoModule,
                    mainViewModule,
                    mainUseCase
                )
            )
        }
    }
}