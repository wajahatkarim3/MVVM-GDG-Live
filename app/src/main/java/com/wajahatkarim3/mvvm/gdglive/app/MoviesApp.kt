package com.wajahatkarim3.mvvm.gdglive.app

import android.app.Application
import com.wajahatkarim3.mvvm.gdglive.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApp : Application()
{
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    fun initKoin()
    {
        startKoin {
            androidContext(applicationContext)
            modules(networkModule)
        }
    }
}