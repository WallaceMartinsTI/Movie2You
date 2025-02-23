package com.wcsm.movie2you.domain.usecase

import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int) : Flow<MoviesResponse<MovieDetails>> {
        return moviesRepository.getMovieDetails(movieId)
    }
}