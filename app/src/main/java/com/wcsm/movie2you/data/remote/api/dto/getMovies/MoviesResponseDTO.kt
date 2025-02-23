package com.wcsm.movie2you.data.remote.api.dto.getMovies

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    @SerializedName("dates")
    val moviesDates: MoviesDates,
    val page: Int,
    @SerializedName("results")
    val movieResults: List<MovieResult>?,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)