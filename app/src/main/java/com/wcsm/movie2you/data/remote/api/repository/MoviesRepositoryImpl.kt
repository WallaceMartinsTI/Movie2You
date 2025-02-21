package com.wcsm.movie2you.data.remote.api.repository

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val tmdbapiService: TMDBAPIService
) : MoviesRepository {
    override suspend fun getNowPlayingMovies(): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val response = tmdbapiService.getNowPlayingMovies(language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val nowPlayingMovies = response.body()?.results

                if(nowPlayingMovies != null) {
                    if(nowPlayingMovies.isEmpty()) {
                        emit(MoviesResponse.Error("Erro ao buscar filmes em exibição: sem filmes disponíveis no momento."))
                    } else {
                        emit(MoviesResponse.Success(nowPlayingMovies.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes em exibição: a lista de filmes está nula."))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes em exibição, favor reportar o erro."))
        }
    }
}