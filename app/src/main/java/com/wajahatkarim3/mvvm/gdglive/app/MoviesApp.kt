package com.wajahatkarim3.mvvm.gdglive.app

import android.app.Application

class MoviesApp : Application()
{
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    fun initKoin()
    {

    }
}