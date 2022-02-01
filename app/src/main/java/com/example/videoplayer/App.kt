package com.example.videoplayer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var application: App
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
          androidContext(this@App)
            modules(com.example.videoplayer.di.modules)
        }
    }

}