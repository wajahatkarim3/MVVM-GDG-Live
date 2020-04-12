package com.wajahatkarim3.mvvm.gdglive.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository
import com.wajahatkarim3.mvvm.gdglive.model.MovieModel

// HomeViewModel is the middle layer between data and HomeActivity. It is getting
// MovieRepository through Dependency Injection through constructor parameter.
class HomeViewModel constructor(private val movieRepository: MovieRespository) : ViewModel()
{
    // We can simply use MutableLiveData instead of using
    // LiveData & MutableLiveData like this. But that will give Activity/Fragment
    // access to modify the data outside the ViewModel.
    // The data only should be read-only outside ViewModel.
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