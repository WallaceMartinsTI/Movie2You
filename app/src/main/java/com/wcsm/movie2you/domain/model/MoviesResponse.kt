package com.wcsm.movie2you.domain.model

sealed class MoviesResponse<out T> {
    data object Loading : MoviesResponse<Nothing>()

    data class Success<out T>(
        val data: T
    ) : MoviesResponse<T>()

    data class Error(
        val message: String
    ) : MoviesResponse<Nothing>()
}