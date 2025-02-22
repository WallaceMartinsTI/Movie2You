package com.wcsm.movie2you.domain.usecase.movieDetails

import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int) : Flow<MoviesResponse<List<MovieDetailsReview>>> {
        return movieDetailsRepository.getMovieDetailsReviews(movieId)
    }
}