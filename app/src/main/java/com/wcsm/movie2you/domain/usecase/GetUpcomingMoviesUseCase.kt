package com.wcsm.movie2you.domain.usecase

import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke() : Flow<MoviesResponse<List<Movie>>> {
        return moviesRepository.getUpcomingMovies()
    }
}