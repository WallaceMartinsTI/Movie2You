package com.wcsm.movie2you.presentation.model

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MoviesListScreen

    @Serializable
    data class MovieDetailsScreen(val movieId: Int)
}