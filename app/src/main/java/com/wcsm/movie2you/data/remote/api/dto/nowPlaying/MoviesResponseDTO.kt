package com.wcsm.movie2you.data.remote.api.dto.nowPlaying

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)