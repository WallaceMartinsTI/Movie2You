package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.usecase.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {
    private val _cachedNowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val cachedNowPlayingMovies = _cachedNowPlayingMovies.asStateFlow()

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    fun getNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getNowPlayingMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {}
                    is MoviesResponse.Error -> {}
                    is MoviesResponse.Success -> {
                        _cachedNowPlayingMovies.value = moviesResponse.data
                        _nowPlayingMovies.value = moviesResponse.data
                    }
                }
            }
        }
    }
}