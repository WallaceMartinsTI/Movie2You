package com.wcsm.movie2you.domain.repository

import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getNowPlayingMovies() : Flow<MoviesResponse<List<Movie>>>
}