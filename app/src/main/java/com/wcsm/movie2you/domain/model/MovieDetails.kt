package com.wcsm.movie2you.domain.model

data class MovieDetails(
    val backdropPath: String,
    val genres: List<String>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Double,

)
