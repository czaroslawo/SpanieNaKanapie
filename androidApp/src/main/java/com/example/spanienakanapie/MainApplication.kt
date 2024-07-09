package com.example.spanienakanapie

import android.app.Application
import com.example.shared.data.modules.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MainApplication)

        }

    }
}