package com.wcsm.movie2you.domain.usecase.movieDetails

import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val moviesDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int) : Flow<MoviesResponse<List<Movie>>> {
        return moviesDetailsRepository.getSimilarMovies(movieId)
    }
}