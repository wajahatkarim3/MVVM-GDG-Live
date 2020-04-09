package com.wajahatkarim3.mvvm.gdglive.ui.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository
import com.wajahatkarim3.mvvm.gdglive.model.ActorModel
import com.wajahatkarim3.mvvm.gdglive.model.MovieModel

class MovieDetailsViewModel(private val movieRespository: MovieRespository) : ViewModel()
{
    private var _uiState = MutableLiveData<MovieDetailsUiState>()
    val uiState: LiveData<MovieDetailsUiState>
        get() = _uiState

    private var _actorsList = MutableLiveData<List<ActorModel>>()
    val actorsList: LiveData<List<ActorModel>>
        get() = _actorsList

    private var _currentMovie = MutableLiveData<MovieModel>()
    val currentMovie : LiveData<MovieModel>
        get() = _currentMovie

    fun loadMovieActors()
    {
        _uiState.value = ActorsLoadingState
        currentMovie.value?.let {
            movieRespository.getMovieActors(it.id,
                success = {list ->
                    _actorsList.value = list
                    _uiState.value = ActorsContentState
                },
                failure = {message ->
                    _uiState.value = ErrorState(message)
                })
        }
    }

    fun setMovieFromIntent(movie: MovieModel)
    {
        _currentMovie.value = movie
    }
}