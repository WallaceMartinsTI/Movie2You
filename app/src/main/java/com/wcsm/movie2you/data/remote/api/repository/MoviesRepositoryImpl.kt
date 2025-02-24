package com.wcsm.movie2you.data.remote.api.repository

import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieCategory
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class MoviesRepositoryImpl(
    private val tmdbApiService: TMDBAPIService
) : MoviesRepository {
    override suspend fun getMoviesByCategory(movieCategory: MovieCategory): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val moviesByCategoryResponse = when(movieCategory) {
                MovieCategory.NOW_PLAYING -> tmdbApiService.getNowPlayingMovies(language = "pt-BR")
                MovieCategory.UPCOMING -> tmdbApiService.getUpcomingMovies(language = "pt-BR")
                MovieCategory.POPULAR -> tmdbApiService.getPopularMovies(language = "pt-BR")
                MovieCategory.TOP_RATED -> tmdbApiService.getTopRatedMovies(language = "pt-BR")
            }

            if(moviesByCategoryResponse.isSuccessful && moviesByCategoryResponse.body() != null) {
                val moviesResultList = moviesByCategoryResponse.body()?.movieResults

                if(moviesResultList != null) {
                    if(moviesResultList.isEmpty()) {
                        emit(MoviesResponse.Error("Sem filmes disponíveis no momento para a categoria ${movieCategory.localLanguageName}."))
                    } else {
                        emit(MoviesResponse.Success(moviesResultList.map { it.toMovie() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar filmes ${movieCategory.localLanguageName}: a lista de filmes está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar filmes ${movieCategory.localLanguageName}: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes ${movieCategory.localLanguageName}, tente mais tarde."))
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<MoviesResponse<MovieDetails>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val movieDetailsResponse = tmdbApiService.getMovieDetails(movieId = movieId, language = "pt-BR")
            val movieDetailsDTO = movieDetailsResponse.body()

            if(movieDetailsResponse.isSuccessful && movieDetailsDTO != null) {
                emit(MoviesResponse.Success(movieDetailsDTO.toMovieDetails()))
            } else {
                emit(MoviesResponse.Error("Erro ao buscar detalhes deste filme: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar detalhes deste filme, tente mais tarde."))
        }
    }

    override suspend fun getMovieReviews(movieId: Int): Flow<MoviesResponse<List<MovieDetailsReview>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val movieReviewsResponse = tmdbApiService.getMovieReviews(movieId = movieId, language = "pt-BR")

            if(movieReviewsResponse.isSuccessful && movieReviewsResponse.body() != null) {
                val movieReviews = movieReviewsResponse.body()?.movieReviewsResults

                if(movieReviews != null) {
                    if(movieReviews.isEmpty()) {
                        emit(MoviesResponse.Error("Sem comentários disponíveis para este filme."))
                    } else {
                        emit(MoviesResponse.Success(movieReviews.map { it.toMovieDetailsComment() }))
                    }
                } else {
                    emit(MoviesResponse.Error("Erro ao buscar comentários deste filme: a lista de comentários está nula."))
                }
            } else {
                emit(MoviesResponse.Error("Erro ao buscar comentários deste filme: falha na requisição."))
            }
        } catch (unknownHostException: UnknownHostException) {
            unknownHostException.printStackTrace()
            emit(MoviesResponse.Error("Sem conexão com a internet, tente novamente mais tarde."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(MoviesResponse.Error("Erro desconhecido ao buscar comentários deste filme, tente mais tarde."))
        }
    }

    override suspend fun getSimilarMovies(movieId: Int): Flow<MoviesResponse<List<Movie>>> = flow {
        try {
            emit(MoviesResponse.Loading)

            val similarMoviesResponse = tmdbApiService.getSimilarMovies(movieId = movieId, language = "pt-BR")

            if(similarMoviesResponse.isSuccessful && similarMoviesResponse.body() != null) {
                val similarMovies = similarMoviesResponse.body()?.movieResults

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
            emit(MoviesResponse.Error("Erro desconhecido ao buscar filmes similares, tente mais tarde."))
        }
    }
}