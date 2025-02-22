package com.wcsm.movie2you.domain.repository

import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.domain.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int) : Flow<MoviesResponse<MovieDetails>>
    suspend fun getMovieDetailsReviews(movieId: Int) : Flow<MoviesResponse<List<MovieDetailsReview>>>
    suspend fun getSimilarMovies(movieId: Int) : Flow<MoviesResponse<List<Movie>>>
}