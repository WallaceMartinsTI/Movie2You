package com.wcsm.movie2you.presentation.model

import com.wcsm.movie2you.domain.model.Movie

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false,
    val movies: List<Movie> = emptyList()
)