package com.liveramp.ats_sdk_android.sample.internal

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ConsentManager.simulateUserConsent(applicationContext)
    }
}