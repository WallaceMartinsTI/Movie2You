package com.wcsm.movie2you.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.wcsm.movie2you.domain.model.MovieDetailsReview
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
class GetMovieReviewsUseCaseTest {

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getMovieReviewsUseCase: GetMovieReviewsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        getMovieReviewsUseCase = GetMovieReviewsUseCase(moviesRepository)
    }

    @Test
    fun getMovieReviewsUseCase_getMovieReviewsFromRepository_shouldEmitSuccessWithMovieReviews() = runTest {
        val movieDetailsReviews = listOf(
            MovieDetailsReview(
                id = "1",
                userName = "user1",
                review = "review1"
            ),
            MovieDetailsReview(
                id = "2",
                userName = "user2",
                review = "review2"
            ),
            MovieDetailsReview(
                id = "3",
                userName = "user3",
                review = "review3"
            )
        )

        Mockito.`when`(moviesRepository.getMovieReviews(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(movieDetailsReviews))
            }
        )

        // GIVEN & WHEN
        getMovieReviewsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(movieDetailsReviews)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviewsUseCase_getMovieReviewsFromRepository_shouldEmitErrorWithAnEmptyListAndNoAvailableReviewsForThisMovieMessage() = runTest {
        Mockito.`when`(moviesRepository.getMovieReviews(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(emptyList()))
            }
        )

        // GIVEN & WHEN
        getMovieReviewsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEmpty()

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviewsUseCase_getMovieReviewsFromRepository_shouldEmitErrorWithNullReviewsListMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar comentários deste filme: a lista de comentários está nula."

        Mockito.`when`(moviesRepository.getMovieReviews(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieReviewsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviewsUseCase_getMovieReviewsFromRepository_shouldEmitErrorWithRequestFailedMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar comentários deste filme: falha na requisição."

        Mockito.`when`(moviesRepository.getMovieReviews(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieReviewsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviewsUseCase_getMovieReviewsFromRepository_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        Mockito.`when`(moviesRepository.getMovieReviews(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieReviewsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviewsUseCase_getMovieReviewsFromRepository_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        val expectedErrorMessage = "Erro desconhecido ao buscar comentários deste filme, tente mais tarde."

        Mockito.`when`(moviesRepository.getMovieReviews(anyInt())).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMovieReviewsUseCase(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

}