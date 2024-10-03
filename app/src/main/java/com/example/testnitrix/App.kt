package com.example.testnitrix

import android.app.Application
import com.example.testnitrix.di.modules.appModule
import com.example.testnitrix.di.modules.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.NONE)
            modules(appModule, dataModule)
        }
    }
}