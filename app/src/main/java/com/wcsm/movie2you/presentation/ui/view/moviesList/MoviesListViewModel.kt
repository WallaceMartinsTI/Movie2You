package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.usecase.GetNowPlayingMoviesUseCase
import com.wcsm.movie2you.domain.usecase.GetPopularMoviesUseCase
import com.wcsm.movie2you.domain.usecase.GetTopRatedMoviesUseCase
import com.wcsm.movie2you.domain.usecase.GetUpcomingMoviesUseCase
import com.wcsm.movie2you.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    //private val _cachedNowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies = _nowPlayingMovies.asStateFlow()

    private val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingMovies = _upcomingMovies.asStateFlow()

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies = _popularMovies.asStateFlow()

    private val _topRatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedMovies = _topRatedMovies.asStateFlow()

    fun resetUiState() {
        _uiState.value = UiState()
    }

    fun getAllMovies() {
        getNowPlayingMovies()
        getUpcomingMovies()
        getPopularMovies()
        getTopRatedMovies()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            /*if(_cachedNowPlayingMovies.value.isNotEmpty()) {
                _nowPlayingMovies.value = _cachedNowPlayingMovies.value
            }*/
            getNowPlayingMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = true,
                        )
                    }
                    is MoviesResponse.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = moviesResponse.message
                        )
                    }
                    is MoviesResponse.Success -> {
                        //_cachedNowPlayingMovies.value = moviesResponse.data
                        _nowPlayingMovies.value = moviesResponse.data

                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            success = true
                        )
                    }
                }
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getUpcomingMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = true,
                        )
                    }
                    is MoviesResponse.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = moviesResponse.message
                        )
                    }
                    is MoviesResponse.Success -> {
                        _upcomingMovies.value = moviesResponse.data

                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            success = true
                        )
                    }
                }
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = true,
                        )
                    }
                    is MoviesResponse.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = moviesResponse.message
                        )
                    }
                    is MoviesResponse.Success -> {
                        _popularMovies.value = moviesResponse.data

                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            success = true
                        )
                    }
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopRatedMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = true,
                        )
                    }
                    is MoviesResponse.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = moviesResponse.message
                        )
                    }
                    is MoviesResponse.Success -> {
                        _topRatedMovies.value = moviesResponse.data

                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            success = true
                        )
                    }
                }
            }
        }
    }
}