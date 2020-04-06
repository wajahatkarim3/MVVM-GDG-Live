package com.wajahatkarim3.mvvm.gdglive.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wajahatkarim3.mvvm.gdglive.data.respository.MovieRespository

class HomeViewModelFactory(val movieRepository: MovieRespository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieRepository) as T
    }
}