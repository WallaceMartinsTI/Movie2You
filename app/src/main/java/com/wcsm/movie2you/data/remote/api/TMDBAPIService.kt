package com.wcsm.movie2you.data.remote.api

import com.wcsm.movie2you.data.remote.api.dto.nowPlaying.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET

interface TMDBAPIService {
    @GET("now_playing")
    suspend fun getNowPlayingMovies() : Response<NowPlayingResponse>

    /*@GET
    suspend fun getTopRatedMovies()

    @GET
    suspend fun getUpcomingMovies()

    @GET
    suspend fun getPopularMovies()

    @GET
    suspend fun getMovieDetails()*/
}

// GET NOW PLAYING
// GET TOP RATED
// GET UPCOMING
// GET POPULAR

// GET DETAILS
// GET SIMILAR MOVIES
// GET REVIEWS