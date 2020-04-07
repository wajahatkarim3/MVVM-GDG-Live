package com.wajahatkarim3.mvvm.gdglive.ui.movie_details

sealed class MovieDetailsUiState

object ActorsLoadingState : MovieDetailsUiState()

object ActorsContentState : MovieDetailsUiState()

class ErrorState(val message: String) : MovieDetailsUiState()