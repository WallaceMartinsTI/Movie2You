package com.wcsm.movie2you.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSimilarMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getSimilarMoviesUseCase: GetSimilarMoviesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        getSimilarMoviesUseCase = GetSimilarMoviesUseCase(moviesRepository)
    }

    @Test
    fun getSimilarMoviesUseCase_getSimilarMoviesFromRepository_shouldEmitSuccessWithSimilarMoviesResultList() = runTest {
        val similarMoviesList = listOf(
            Movie(id = 1, posterPath = "poster1"),
            Movie(id = 2, posterPath = "poster2"),
            Movie(id = 3, posterPath = "poster3")
        )

        Mockito.`when`(moviesRepository.getSimilarMovies(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(similarMoviesList))
            }
        )

        // GIVEN & WHEN
        getSimilarMoviesUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(similarMoviesList)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMoviesUseCase_getSimilarMoviesFromRepository_shouldEmitErrorWithNoAvailableSimilarMoviesForThisMovieMessage() = runTest {
        val expectedErrorMessage = "Sem filmes similares no momento."

        Mockito.`when`(moviesRepository.getSimilarMovies(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getSimilarMoviesUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMoviesUseCase_getSimilarMoviesFromRepository_shouldEmitErrorWithNullSimilarMoviesListMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar filmes similares: a lista de filmes está nula."

        Mockito.`when`(moviesRepository.getSimilarMovies(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getSimilarMoviesUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMoviesUseCase_getSimilarMoviesFromRepository_shouldEmitErrorWithRequestFailedMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar filmes similares: falha na requisição."

        Mockito.`when`(moviesRepository.getSimilarMovies(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getSimilarMoviesUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMoviesUseCase_getSimilarMoviesFromRepository_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        Mockito.`when`(moviesRepository.getSimilarMovies(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getSimilarMoviesUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMoviesUseCase_getSimilarMoviesFromRepository_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        val expectedErrorMessage = "Erro desconhecido ao buscar filmes similares, tente mais tarde."

        Mockito.`when`(moviesRepository.getSimilarMovies(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getSimilarMoviesUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

}