package com.wcsm.movie2you.di

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.data.remote.api.repository.MovieDetailsRepositoryImpl
import com.wcsm.movie2you.domain.repository.MovieDetailsRepository
import com.wcsm.movie2you.domain.usecase.movieDetails.GetMovieDetailsUseCase
import com.wcsm.movie2you.domain.usecase.movieDetails.GetMovieReviewsUseCase
import com.wcsm.movie2you.domain.usecase.movieDetails.GetSimilarMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MovieDetailsModule {

    @Provides
    fun providesMovieDetailsRepository(
        tmdbapiService: TMDBAPIService
    ) : MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(tmdbapiService)
    }

    @Provides
    fun providesGetMovieDetails(
        movieDetailsRepository: MovieDetailsRepository
    ) : GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(movieDetailsRepository)
    }

    @Provides
    fun providesGetMovieReviews(
        movieDetailsRepository: MovieDetailsRepository
    ) : GetMovieReviewsUseCase {
        return GetMovieReviewsUseCase(movieDetailsRepository)
    }

    @Provides
    fun providesGetSimilarMoviesUseCase(
        movieDetailsRepository: MovieDetailsRepository
    ) : GetSimilarMoviesUseCase {
        return GetSimilarMoviesUseCase(movieDetailsRepository)
    }

}