package com.wajahatkarim3.mvvm.gdglive.di

import com.wajahatkarim3.mvvm.gdglive.app.Constants
import com.wajahatkarim3.mvvm.gdglive.data.remote.MovieApiService
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This is Network Module responsible for providing
// Retrofit API service object (MovieApiService) and the
// MovieRepository singleton.
val networkModule = module {

    // MovieApiService singleton. Where we need this, we only will call
    // the get() method. And Koin will provide us the singleton.
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    // MovieRepository singleton. Same, when we need this, we will call get() and
    // Koin will provide us the single instance.
    // Also note how get() is being called in the constructor because it needs
    // MovieApiService singelton and we have simply called get().
    single {
        MovieRespository(get())
    }

}