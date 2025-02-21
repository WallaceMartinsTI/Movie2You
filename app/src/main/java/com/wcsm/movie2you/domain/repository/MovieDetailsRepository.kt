package com.wcsm.movie2you.domain.repository

import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails() : Flow<MoviesResponse<MovieDetails>>
}