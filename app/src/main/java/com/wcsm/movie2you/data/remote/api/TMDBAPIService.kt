package com.wcsm.movie2you.data.remote.api

import com.wcsm.movie2you.data.remote.api.dto.nowPlaying.MoviesResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPIService {
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
}

// GET NOW PLAYING
// GET TOP RATED
// GET UPCOMING
// GET POPULAR

// GET DETAILS
// GET SIMILAR MOVIES
// GET REVIEWS