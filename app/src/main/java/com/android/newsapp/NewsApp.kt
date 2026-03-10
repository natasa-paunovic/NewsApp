package com.android.newsapp

import android.app.Application
import com.android.newsapp.di.appModule
import com.android.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApp)
            modules(appModule, viewModelModule)
        }
    }
}