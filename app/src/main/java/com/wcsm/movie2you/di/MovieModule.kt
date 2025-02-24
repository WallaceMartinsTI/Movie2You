package com.wcsm.movie2you.di

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.data.remote.api.repository.MoviesRepositoryImpl
import com.wcsm.movie2you.domain.repository.MoviesRepository
import com.wcsm.movie2you.domain.usecase.GetMovieDetailsUseCase
import com.wcsm.movie2you.domain.usecase.GetMovieReviewsUseCase
import com.wcsm.movie2you.domain.usecase.GetMoviesByCategoryUseCase
import com.wcsm.movie2you.domain.usecase.GetSimilarMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MovieModule {

    @Provides
    fun provideMoviesRepository(
        tmdbApiService: TMDBAPIService
    ) : MoviesRepository {
        return MoviesRepositoryImpl(tmdbApiService)
    }

    @Provides
    fun provideGetMoviesByCategoryUseCase(
        moviesRepository: MoviesRepository
    ) : GetMoviesByCategoryUseCase {
        return GetMoviesByCategoryUseCase(moviesRepository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(
        moviesRepository: MoviesRepository
    ) : GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(moviesRepository)
    }

    @Provides
    fun provideGetMovieReviewsUseCase(
        moviesRepository: MoviesRepository
    ) : GetMovieReviewsUseCase {
        return GetMovieReviewsUseCase(moviesRepository)
    }

    @Provides
    fun provideGetSimilarMoviesUseCase(
        moviesRepository: MoviesRepository
    ) : GetSimilarMoviesUseCase {
        return GetSimilarMoviesUseCase(moviesRepository)
    }

}