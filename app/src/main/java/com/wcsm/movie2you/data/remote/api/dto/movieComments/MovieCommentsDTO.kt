package com.wcsm.movie2you.data.remote.api.dto.movieComments

import com.google.gson.annotations.SerializedName

data class MovieCommentsDTO(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)