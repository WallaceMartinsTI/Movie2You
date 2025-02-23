package com.wcsm.movie2you.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.wcsm.movie2you.domain.model.MovieDetails
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
class GetMovieDetailsUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        getMovieDetailsUseCase = GetMovieDetailsUseCase(moviesRepository)
    }

    @Test
    fun getMovieDetailsUseCase_getMovieDetailsFromRepository_shouldEmitSuccessWithMovieDetails() = runTest {
        val fakeMovieDetails = MovieDetails(
            backdropPath = "backdropPath1",
            genres = emptyList(),
            id = 1,
            overview = "overview1",
            posterPath = "posterPath1",
            runtime = "1",
            title = "movieTitle1",
            voteAverage = "1.0",
        )

        Mockito.`when`(moviesRepository.getMovieDetails(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(fakeMovieDetails))
            }
        )

        // GIVEN & WHEN
        getMovieDetailsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(fakeMovieDetails)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetailsUseCase_getMovieDetailsFromRepository_shouldEmitErrorWithRequestFailedMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar detalhes deste filme: falha na requisição."

        Mockito.`when`(moviesRepository.getMovieDetails(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieDetailsUseCase(-1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(
                expectedErrorMessage
            )

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetailsUseCase_getMovieDetailsFromRepository_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        Mockito.`when`(moviesRepository.getMovieDetails(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieDetailsUseCase(-1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(
                expectedErrorMessage
            )

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetailsUseCase_getMovieDetailsFromRepository_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        val expectedErrorMessage = "Erro desconhecido ao buscar detalhes deste filme, favor reportar o erro."

        Mockito.`when`(moviesRepository.getMovieDetails(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieDetailsUseCase(-1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(
                expectedErrorMessage
            )

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }
}
