package com.wcsm.movie2you.data.remote.api.dto.movieReviews

import com.google.gson.annotations.SerializedName

data class MovieReviewsDTO(
    val id: Int,
    val page: Int,
    @SerializedName("results")
    val movieReviewsResults: List<MovieReviewsResult>?,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)