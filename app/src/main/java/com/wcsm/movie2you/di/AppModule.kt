package com.wcsm.movie2you.di

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.data.remote.api.repository.MoviesRepositoryImpl
import com.wcsm.movie2you.domain.repository.MoviesRepository
import com.wcsm.movie2you.domain.usecase.GetNowPlayingMoviesUseCase
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

}