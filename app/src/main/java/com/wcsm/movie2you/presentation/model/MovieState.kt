package com.wcsm.movie2you.presentation.model

import com.wcsm.movie2you.domain.model.Movie

data class MovieState(
    val movies: List<Movie> = emptyList(),
    val errorMessage: String = ""
)