package com.wcsm.movie2you.data.remote.api.repository

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class MovieDetailsRepositoryImpl(
    private val tmdbApiService: TMDBAPIService
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): Flow<MoviesResponse<MovieDetails>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val teste = if(movieId == 238) 999 else movieId
            val response = tmdbApiService.getMovieDetails(movieId = teste, language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val movieDetails = response.body()

                if(movieDetails != null) {
                    emit(MoviesResponse.Success(movieDetails.toMovieDetails()))
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar detalhes deste filme: sem detalhes disponíveis."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar detalhes deste filme: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar detalhes deste filme, favor reportar o erro."))
        }
    }

    override suspend fun getMovieDetailsReviews(movieId: Int): Flow<MoviesResponse<List<MovieDetailsReview>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val response = tmdbApiService.getMovieReviews(movieId = movieId, language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val movieReviews = response.body()?.results

                if(movieReviews != null) {
                    if(movieReviews.isEmpty()) {
                        emit(MoviesResponse.Error("Sem comentários disponíveis para este filme."))
                    } else {
                        emit(MoviesResponse.Success(movieReviews.map { it.toMovieDetailsComment() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar comentários deste filme: sem detalhes disponíveis."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar comentários deste filme: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar comentários deste filme, favor reportar o erro."))
        }
    }

    override suspend fun getSimilarMovies(movieId: Int): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val teste = if(movieId == 240) 999 else movieId
            val response = tmdbApiService.getSimilarMovies(movieId = teste, language = "pt-BR")

            if(response.isSuccessful && response.body() != null) {
                val similarMovies = response.body()?.results

                if(similarMovies != null) {
                    if(similarMovies.isEmpty()) {
                        emit(MoviesResponse.Error("Sem filmes similares no momento."))
                    } else {
                        emit(MoviesResponse.Success(similarMovies.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes similares: a lista de filmes está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar filmes similares: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes similares, favor reportar o erro."))
        }
    }
}