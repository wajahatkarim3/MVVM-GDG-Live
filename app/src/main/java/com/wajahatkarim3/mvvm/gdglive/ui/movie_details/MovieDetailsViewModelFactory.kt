package com.wajahatkarim3.mvvm.gdglive.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository

class MovieDetailsViewModelFactory(private val movieRespository: MovieRespository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movieRespository) as T
    }
}