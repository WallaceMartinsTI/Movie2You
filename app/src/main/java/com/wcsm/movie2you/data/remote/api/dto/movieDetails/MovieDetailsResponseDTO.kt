package com.wcsm.movie2you.data.remote.api.dto.movieDetails

import com.google.gson.annotations.SerializedName
import com.wcsm.movie2you.domain.model.MovieDetails

data class MovieDetailsResponseDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toMovieDetails() : MovieDetails {
        val movieGenres = this.genres.map { it.name }
        val movieDuration = formatTime(this.runtime)

        return MovieDetails(
            backdropPath = this.backdropPath,
            genres = movieGenres,
            id = this.id,
            overview = this.overview,
            posterPath = this.posterPath,
            runtime = movieDuration,
            title = this.title,
            voteAverage = "%.1f".format(this.voteAverage).replace(",", ".")
        )
    }
}

private fun formatTime(minutes: Int) : String {
    val hours = minutes / 60
    val remainingMinutes = minutes % 60

    return buildString {
        if (hours > 0) append("$hours hora(s) ")
        if (remainingMinutes > 0 || hours == 0) append("$remainingMinutes minuto(s)")
    }.trim()
}