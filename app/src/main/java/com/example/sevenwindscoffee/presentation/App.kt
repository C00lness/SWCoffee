package com.example.sevenwindscoffee.presentation

import android.app.Application
import com.example.sevenwindscoffee.di.appModule
import com.example.sevenwindscoffee.di.dataModule
import com.example.sevenwindscoffee.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, appModule)}
    }
}