package com.wcsm.movie2you.presentation.model

data class UiState<out T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false,
    val data: T
)