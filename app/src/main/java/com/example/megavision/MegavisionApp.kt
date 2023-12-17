package com.example.megavision

import android.app.Application
import com.example.megavision.di.networkModule
import com.example.megavision.di.repositoryModule
import com.example.megavision.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MegavisionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MegavisionApp)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}