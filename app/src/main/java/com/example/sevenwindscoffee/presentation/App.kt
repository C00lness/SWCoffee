package com.example.sevenwindscoffee.presentation

import android.app.Application
import com.example.sevenwindscoffee.di.appModule
import com.example.sevenwindscoffee.di.dataModule
import com.example.sevenwindscoffee.di.domainModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sulgik.mapkit.MapKit

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, appModule)}
            MapKit.setApiKey("75b0650d-4f5c-4f51-9f91-21d7ec20f684")
    }
}