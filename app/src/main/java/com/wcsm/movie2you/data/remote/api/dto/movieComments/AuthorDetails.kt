package com.wcsm.movie2you.data.remote.api.dto.movieComments

import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String,
    val name: String,
    val rating: Double,
    val username: String
)