package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.usecase.moviesList.GetNowPlayingMoviesUseCase
import com.wcsm.movie2you.domain.usecase.moviesList.GetPopularMoviesUseCase
import com.wcsm.movie2you.domain.usecase.moviesList.GetTopRatedMoviesUseCase
import com.wcsm.movie2you.domain.usecase.moviesList.GetUpcomingMoviesUseCase
import com.wcsm.movie2you.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    /* FAZER CACHE DOS FILMES */

    private val _nowPlayingMoviesState = MutableStateFlow(UiState<List<Movie>>(data = emptyList()))
    val nowPlayingMoviesState = _nowPlayingMoviesState.asStateFlow()

    private val _upcomingMoviesState = MutableStateFlow(UiState<List<Movie>>(data = emptyList()))
    val upcomingMoviesState = _upcomingMoviesState.asStateFlow()

    private val _popularMoviesState = MutableStateFlow(UiState<List<Movie>>(data = emptyList()))
    val popularMoviesState = _popularMoviesState.asStateFlow()

    private val _topRatedMoviesState = MutableStateFlow(UiState<List<Movie>>(data = emptyList()))
    val topRatedMoviesState = _topRatedMoviesState.asStateFlow()

    fun getAllMovies() {
        getNowPlayingMovies()
        getUpcomingMovies()
        getPopularMovies()
        getTopRatedMovies()
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getNowPlayingMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _nowPlayingMoviesState.value = UiState(
                            isLoading = true,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Error -> {
                        _nowPlayingMoviesState.value = UiState(
                            error = moviesResponse.errorMessage,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Success -> {
                        _nowPlayingMoviesState.value = UiState(
                            success = true,
                            data = moviesResponse.data
                        )
                    }
                }
            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getUpcomingMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _upcomingMoviesState.value = UiState(
                            isLoading = true,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Error -> {
                        _upcomingMoviesState.value = UiState(
                            error = moviesResponse.errorMessage,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Success -> {
                        _upcomingMoviesState.value = UiState(
                            success = true,
                            data = moviesResponse.data
                        )
                    }
                }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _popularMoviesState.value = UiState(
                            isLoading = true,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Error -> {
                        _popularMoviesState.value = UiState(
                            error = moviesResponse.errorMessage,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Success -> {
                        _popularMoviesState.value = UiState(
                            success = true,
                            data = moviesResponse.data
                        )
                    }
                }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopRatedMoviesUseCase().collect { moviesResponse ->
                when(moviesResponse) {
                    is MoviesResponse.Loading -> {
                        _topRatedMoviesState.value = UiState(
                            isLoading = true,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Error -> {
                        _topRatedMoviesState.value = UiState(
                            error = moviesResponse.errorMessage,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Success -> {
                        _topRatedMoviesState.value = UiState(
                            success = true,
                            data = moviesResponse.data
                        )
                    }
                }
            }
        }
    }
}