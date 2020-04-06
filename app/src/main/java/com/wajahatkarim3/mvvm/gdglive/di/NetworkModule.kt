package com.wajahatkarim3.mvvm.gdglive.di

import com.wajahatkarim3.mvvm.gdglive.app.Constants
import com.wajahatkarim3.mvvm.gdglive.data.remote.MovieApiService
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    single {
        MovieRespository(get())
    }

}