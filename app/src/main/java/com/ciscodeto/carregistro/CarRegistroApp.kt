package com.ciscodeto.carregistro

import android.app.Application
import com.ciscodeto.carregistro.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CarRegistroApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CarRegistroApp)
            modules(appModules())
        }
    }
}