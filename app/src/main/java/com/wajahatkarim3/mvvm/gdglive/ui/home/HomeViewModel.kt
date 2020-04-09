package com.wajahatkarim3.mvvm.gdglive.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository
import com.wajahatkarim3.mvvm.gdglive.model.MovieModel

class HomeViewModel constructor(private val movieRepository: MovieRespository) : ViewModel()
{
    private var _uiState = MutableLiveData<HomeUiState>()
    val uiState: LiveData<HomeUiState>
        get() = _uiState

    private var _moviesList = MutableLiveData<List<MovieModel>>()
    val moviesList: LiveData<List<MovieModel>>
        get() = _moviesList

    fun loadMovies()
    {
        _uiState.value = LoadingState
        movieRepository.getLatestMovies(
            success = { movies ->
                _moviesList.value = movies
                _uiState.value = MoviesListState
            },
            failure = {message ->
                _uiState.value = ErrorState(message)
            }
        )
    }
}