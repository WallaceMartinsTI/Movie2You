package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.model.MovieCategory
import com.wcsm.movie2you.domain.usecase.GetMoviesByCategoryUseCase
import com.wcsm.movie2you.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<Map<MovieCategory, UiState<List<Movie>>>>(emptyMap())
    val movies = _movies.asStateFlow()

    private val _cachedMovies = MutableStateFlow<Map<MovieCategory, List<Movie>>>(emptyMap())

    fun getAllMovies() {
        MovieCategory.entries.forEach { movieCategory ->
            getMoviesByCategory(movieCategory)
        }
    }

    fun getMoviesByCategory(movieCategory: MovieCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            val cachedMovies = _cachedMovies.value[movieCategory]
            if(cachedMovies != null) {
                _movies.value = _movies.value.toMutableMap().apply {
                    put(movieCategory, UiState(data = cachedMovies))
                }
                return@launch
            }

            getMoviesByCategoryUseCase(movieCategory).collect { movieResponse ->
                val newState = when(movieResponse) {
                    is MoviesResponse.Loading -> UiState(isLoading = true, data = emptyList())
                    is MoviesResponse.Error -> UiState(error = movieResponse.errorMessage, data = emptyList())
                    is MoviesResponse.Success -> {
                        _cachedMovies.value = _cachedMovies.value.toMutableMap().apply {
                            put(movieCategory, movieResponse.data)
                        }
                        UiState(success = true, data = movieResponse.data)
                    }
                }

                _movies.value = _movies.value.toMutableMap().apply {
                    put(movieCategory, newState)
                }
            }
        }
    }
}