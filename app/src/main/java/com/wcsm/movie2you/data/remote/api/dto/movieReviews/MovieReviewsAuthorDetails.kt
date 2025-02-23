package com.wcsm.movie2you.data.remote.api.dto.movieReviews

import com.google.gson.annotations.SerializedName

data class MovieReviewsAuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String,
    val name: String,
    val rating: Double,
    val username: String
)