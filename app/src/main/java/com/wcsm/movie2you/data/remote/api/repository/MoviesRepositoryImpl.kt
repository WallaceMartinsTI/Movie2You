package com.wcsm.movie2you.data.remote.api.repository

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class MoviesRepositoryImpl(
    private val tmdbApiService: TMDBAPIService
) : MoviesRepository {
    override suspend fun getNowPlayingMovies(): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val response = tmdbApiService.getNowPlayingMovies(language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val nowPlayingMovies = response.body()?.results

                if(nowPlayingMovies != null) {
                    if(nowPlayingMovies.isEmpty()) {
                        emit(MoviesResponse.Error("Sem filmes disponíveis no momento para a categoria  Em Exibição."))
                    } else {
                        emit(MoviesResponse.Success(nowPlayingMovies.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes em exibição: a lista de filmes está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar filmes em exibição: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes em exibição, favor reportar o erro."))
        }
    }

    override suspend fun getUpcomingMovies(): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val response = tmdbApiService.getUpcomingMovies(language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val upcomingMovies = response.body()?.results

                if(upcomingMovies != null) {
                    if(upcomingMovies.isEmpty()) {
                        emit(MoviesResponse.Error("Sem filmes disponíveis no momento para a categoria \"Em Breve\"."))
                    } else {
                        emit(MoviesResponse.Success(upcomingMovies.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes \"Em Breve\": a lista de filmes está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar filmes \"Em Breve\": falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes \"Em Breve\", favor reportar o erro."))
        }
    }

    override suspend fun getPopularMovies(): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val response = tmdbApiService.getPopularMovies(language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val popularMovies = response.body()?.results

                if(popularMovies != null) {
                    if(popularMovies.isEmpty()) {
                        emit(MoviesResponse.Error("Sem filmes disponíveis no momento para a categoria Mais Populares."))
                    } else {
                        emit(MoviesResponse.Success(popularMovies.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes populares: a lista de filmes está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar filmes populares: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes populares, favor reportar o erro."))
        }
    }

    override suspend fun getTopRatedMovies(): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val response = tmdbApiService.getTopRatedMovies(language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val topRatedMovies = response.body()?.results

                if(topRatedMovies != null) {
                    if(topRatedMovies.isEmpty()) {
                        emit(MoviesResponse.Error("Sem filmes disponíveis no momento para a categoria Melhores Avaliados."))
                    } else {
                        emit(MoviesResponse.Success(topRatedMovies.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes melhores avaliados: a lista de filmes está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar filmes melhores avaliados: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes melhores avaliados, favor reportar o erro."))
        }
    }
}