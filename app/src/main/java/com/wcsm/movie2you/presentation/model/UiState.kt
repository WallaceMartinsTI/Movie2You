package com.wcsm.movie2you.presentation.model

data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false,
)