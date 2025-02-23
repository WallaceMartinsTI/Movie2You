package com.wcsm.movie2you.data.remote.api.dto.movieReviews

import com.google.gson.annotations.SerializedName
import com.wcsm.movie2you.domain.model.MovieDetailsReview

data class MovieReviewsResult(
    val author: String,
    @SerializedName("author_details")
    val movieReviewsAuthorDetails: MovieReviewsAuthorDetails,
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String
) {
    fun toMovieDetailsComment() : MovieDetailsReview {
        return MovieDetailsReview(
            id = this.id,
            userName = this.movieReviewsAuthorDetails.username,
            review = this.content
        )
    }
}