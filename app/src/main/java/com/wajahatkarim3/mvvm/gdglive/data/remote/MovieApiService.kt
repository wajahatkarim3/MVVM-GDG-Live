package com.wajahatkarim3.mvvm.gdglive.data.remote

import com.wajahatkarim3.mvvm.gdglive.data.remote.responses.ActorsListResponse
import com.wajahatkarim3.mvvm.gdglive.data.remote.responses.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    fun getNowPlaying(
        @Query("api_key") apiKey: String
    ) : Call<MoviesListResponse>

    @GET("movie/{movie_id}/credits")
    fun getActorsList(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ) : Call<ActorsListResponse>
}