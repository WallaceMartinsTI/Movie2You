package com.wcsm.movie2you.data.remote.api.dto.movieComments

import com.google.gson.annotations.SerializedName
import com.wcsm.movie2you.domain.model.MovieDetailsReview

data class Result(
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
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
            userName = this.authorDetails.username,
            comment = this.content
        )
    }
}