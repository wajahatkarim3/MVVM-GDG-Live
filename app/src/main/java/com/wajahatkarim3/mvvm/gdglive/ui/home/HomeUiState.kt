package com.wajahatkarim3.mvvm.gdglive.ui.home

sealed class HomeUiState

object LoadingState: HomeUiState()

object MoviesListState: HomeUiState()

class ErrorState(val message: String): HomeUiState()

class NoMoviesState(val message: String) : HomeUiState()