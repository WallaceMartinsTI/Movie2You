package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.usecase.GetMovieDetailsUseCase
import com.wcsm.movie2you.domain.usecase.GetMovieReviewsUseCase
import com.wcsm.movie2you.domain.usecase.GetSimilarMoviesUseCase
import com.wcsm.movie2you.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<UiState<MovieDetails?>>(UiState(data = null))
    val movieDetails = _movieDetails.asStateFlow()

    private val _cachedMovieDetails = MutableStateFlow<Map<Int, MovieDetails>>(emptyMap())

    private val _movieReviews = MutableStateFlow<UiState<List<MovieDetailsReview>?>>(UiState(data = null))
    val movieReviews = _movieReviews.asStateFlow()

    private val _cachedMovieReviews = MutableStateFlow<Map<Int, List<MovieDetailsReview>>>(emptyMap())

    private val _similarMovies = MutableStateFlow<UiState<List<Movie>>>(UiState(data = emptyList()))
    val similarMovies = _similarMovies.asStateFlow()

    private val _cachedSimilarMovies = MutableStateFlow<Map<Int, List<Movie>>>(emptyMap())

    fun getAllMovieData(movieId: Int) {
        getMovieDetails(movieId)
        getMovieReviews(movieId)
        getSimilarMovies(movieId)
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val cachedMovieDetails = _cachedMovieDetails.value[movieId]
            if(cachedMovieDetails != null) {
                _movieDetails.value = UiState(data = cachedMovieDetails)
                return@launch
            }

            getMovieDetailsUseCase(movieId).collect { movieDetailsResponse ->
                when(movieDetailsResponse) {
                    is MoviesResponse.Loading -> {
                        _movieDetails.value = UiState(
                            isLoading = true,
                            data = null
                        )
                    }
                    is MoviesResponse.Error -> {
                        _movieDetails.value = UiState(
                            error = movieDetailsResponse.errorMessage,
                            data = null
                        )
                    }
                    is MoviesResponse.Success -> {
                        _cachedMovieDetails.value = _cachedMovieDetails.value.toMutableMap().apply {
                            put(movieId, movieDetailsResponse.data)
                        }
                        _movieDetails.value = UiState(
                            success = true,
                            data = movieDetailsResponse.data
                        )
                    }
                }
            }
        }
    }

    fun getMovieReviews(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val cachedMovieReviews = _cachedMovieReviews.value[movieId]
            if(cachedMovieReviews != null) {
                _movieReviews.value = UiState(data = cachedMovieReviews)
                return@launch
            }

            getMovieReviewsUseCase(movieId).collect { movieReviewsResponse ->
                when(movieReviewsResponse) {
                    is MoviesResponse.Loading -> {
                        _movieReviews.value = UiState(
                            isLoading = true,
                            data = null
                        )
                    }
                    is MoviesResponse.Error -> {
                        _movieReviews.value = UiState(
                            error = movieReviewsResponse.errorMessage,
                            data = null
                        )
                    }
                    is MoviesResponse.Success -> {
                        _cachedMovieReviews.value = _cachedMovieReviews.value.toMutableMap().apply {
                            put(movieId, movieReviewsResponse.data)
                        }

                        _movieReviews.value = UiState(
                            success = true,
                            data = movieReviewsResponse.data
                        )
                    }
                }
            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val cachedSimilarMovies = _cachedSimilarMovies.value[movieId]
            if(cachedSimilarMovies != null) {
                _similarMovies.value = UiState(data = cachedSimilarMovies)
                return@launch
            }

            getSimilarMoviesUseCase(movieId).collect { similarMovieResponse ->
                when(similarMovieResponse) {
                    is MoviesResponse.Loading -> {
                        _similarMovies.value = UiState(
                            isLoading = true,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Error -> {
                        _similarMovies.value = UiState(
                            error = similarMovieResponse.errorMessage,
                            data = emptyList()
                        )
                    }
                    is MoviesResponse.Success -> {
                        _cachedSimilarMovies.value = _cachedSimilarMovies.value.toMutableMap().apply {
                            put(movieId, similarMovieResponse.data)
                        }

                        _similarMovies.value = UiState(
                            success = true,
                            data = similarMovieResponse.data
                        )
                    }
                }
            }
        }
    }
}