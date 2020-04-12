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
        // This is where Koin is being initialized and put in action.
        startKoin {
            // Sometimes, Repository needs Android Context. Koin handles this case
            // and provides us the Context whereever we need it.
            androidContext(applicationContext)

            // List of all the modules to be handled by Koin. This code have only
            // NetworkModule.
            modules(networkModule)
        }
    }
}