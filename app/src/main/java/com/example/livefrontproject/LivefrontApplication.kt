package com.example.livefrontproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LivefrontApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
