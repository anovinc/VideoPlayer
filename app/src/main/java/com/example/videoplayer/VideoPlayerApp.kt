package com.example.videoplayer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoPlayerApp : Application() {

    companion object {
        lateinit var application: VideoPlayerApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
          androidContext(this@VideoPlayerApp)
            modules(com.example.videoplayer.di.modules)
        }
    }

}