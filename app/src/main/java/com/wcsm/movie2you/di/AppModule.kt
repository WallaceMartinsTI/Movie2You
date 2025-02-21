package com.wcsm.movie2you.di

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.data.remote.api.repository.MoviesRepositoryImpl
import com.wcsm.movie2you.domain.repository.MoviesRepository
import com.wcsm.movie2you.domain.usecase.GetNowPlayingMoviesUseCase
import com.wcsm.movie2you.domain.usecase.GetPopularMoviesUseCase
import com.wcsm.movie2you.domain.usecase.GetTopRatedMoviesUseCase
import com.wcsm.movie2you.domain.usecase.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun providesMoviesRepository(
        tmdbApiService: TMDBAPIService
    ) : MoviesRepository {
        return MoviesRepositoryImpl(tmdbApiService)
    }

    @Provides
    fun providesGetNowPlayingMoviesUseCase(
        moviesRepository: MoviesRepository
    ) : GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(moviesRepository)
    }

    @Provides
    fun providesGetUpcomingMoviesUseCase(
        moviesRepository: MoviesRepository
    ) : GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(moviesRepository)
    }

    @Provides
    fun providesGetPopularMoviesUseCase(
        moviesRepository: MoviesRepository
    ) : GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(moviesRepository)
    }

    @Provides
    fun providesGetTopRatedMoviesUseCase(
        moviesRepository: MoviesRepository
    ) : GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(moviesRepository)
    }

}