package com.wcsm.movie2you.data.remote.api.dto.movieDetails

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)