package com.l3on1kl.sequeniatestapp

import android.app.Application
import com.l3on1kl.sequeniatestapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SequeniaTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SequeniaTestApp)
            modules(appModule)
        }
    }
}
