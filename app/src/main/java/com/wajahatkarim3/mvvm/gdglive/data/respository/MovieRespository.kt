package com.wajahatkarim3.mvvm.gdglive.data.respository

import com.wajahatkarim3.mvvm.gdglive.app.Constants
import com.wajahatkarim3.mvvm.gdglive.data.remote.MovieApiService
import com.wajahatkarim3.mvvm.gdglive.ktx.RetrofitCallback
import com.wajahatkarim3.mvvm.gdglive.model.ActorModel
import com.wajahatkarim3.mvvm.gdglive.model.MovieModel

class MovieRespository constructor(private val moviesApiService: MovieApiService)
{
    fun getLatestMovies(success: (movieList: List<MovieModel>) -> Unit, failure: (message: String) -> Unit)
    {
        moviesApiService.getNowPlaying(Constants.API_KEY)
            .enqueue(RetrofitCallback {
                onSuccess { call, response ->
                    if (response != null && response.isSuccessful && response.body()?.success == true) {
                        var list = arrayListOf<MovieModel>()
                        response.body()?.results?.let {
                            list.addAll(it)
                        }
                        success.invoke(list)
                    }
                    else failure.invoke("Couldn't find movies!")
                }

                onError { call, throwable ->
                    failure.invoke(throwable?.localizedMessage ?: "Unknown Error Occurred!")
                }
            })
    }

    fun getMovieActors(movieId: Int, success: (actorsList: List<ActorModel>) -> Unit, failure: (message: String) -> Unit)
    {
        moviesApiService.getActorsList(movieId.toString(), Constants.API_KEY)
            .enqueue(RetrofitCallback {
                onSuccess { call, response ->
                    if (response != null && response.isSuccessful && response.body()?.success == true)
                    {
                        var list = arrayListOf<ActorModel>()
                        list.addAll(response.body()?.actorsList ?: emptyList())
                        success.invoke(list)
                    }
                    else failure.invoke("Couldn't find movies!")
                }
                onError { call, throwable ->
                    failure.invoke(throwable?.localizedMessage ?: "Unknown Error Occured!")
                }
            })
    }
}