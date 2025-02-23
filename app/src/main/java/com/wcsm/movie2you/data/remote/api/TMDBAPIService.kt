package com.wcsm.movie2you.data.remote.api

import com.wcsm.movie2you.data.remote.api.dto.getMovies.MoviesResponseDTO
import com.wcsm.movie2you.data.remote.api.dto.movieReviews.MovieReviewsDTO
import com.wcsm.movie2you.data.remote.api.dto.movieDetails.MovieDetailsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBAPIService {
    // Movies List
    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String
    ) : Response<MoviesResponseDTO>

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String
    ) : Response<MoviesResponseDTO>

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("language") language: String
    ) : Response<MoviesResponseDTO>

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String
    ) : Response<MoviesResponseDTO>

    // Movie Details
    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ) : Response<MovieDetailsResponseDTO>

    @GET("{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ) : Response<MovieReviewsDTO>

    @GET("{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ) : Response<MoviesResponseDTO>
}