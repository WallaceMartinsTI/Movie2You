package com.wcsm.movie2you.domain.repository

import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieCategory
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesByCategory(movieCategory: MovieCategory) : Flow<MoviesResponse<List<Movie>>>
    suspend fun getMovieDetails(movieId: Int) : Flow<MoviesResponse<MovieDetails>>
    suspend fun getMovieReviews(movieId: Int) : Flow<MoviesResponse<List<MovieDetailsReview>>>
    suspend fun getSimilarMovies(movieId: Int) : Flow<MoviesResponse<List<Movie>>>
}