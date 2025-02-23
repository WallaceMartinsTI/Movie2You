package com.wcsm.movie2you.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieCategory
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesByCategoryUseCaseTest {
    private val fakeMoviesList = listOf(
        Movie(id = 1, posterPath = "poster1"),
        Movie(id = 2, posterPath = "poster2"),
        Movie(id = 3, posterPath = "poster3")
    )

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    private lateinit var getMoviesByCategoryUseCase: GetMoviesByCategoryUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        getMoviesByCategoryUseCase = GetMoviesByCategoryUseCase(moviesRepository)
    }

    @Test
    fun getMoviesByCategoryUseCase_getNowPlayingMoviesFromRepository_shouldEmitSuccessWithMoviesList() = runTest {
        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(fakeMoviesList))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(fakeMoviesList)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_getUpcomingMoviesFromRepository_shouldEmitSuccessWithMoviesList() = runTest {
        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.UPCOMING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(fakeMoviesList))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.UPCOMING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(fakeMoviesList)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_getPopularMoviesFromRepository_shouldEmitSuccessWithMoviesList() = runTest {
        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.POPULAR)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(fakeMoviesList))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.POPULAR).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(fakeMoviesList)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_getTopRatedMoviesFromRepository_shouldEmitSuccessWithMoviesList() = runTest {
        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.TOP_RATED)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Success(fakeMoviesList))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.TOP_RATED).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(fakeMoviesList)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    // getMoviesByCategoryUseCase errors will be tested with the NowPlaying movie type

    @Test
    fun getMoviesByCategoryUseCase_requestMoviesFromRepository_shouldEmitErrorWithNoAvailableMoviesForThisCategoryMessage() = runTest {
        val expectedErrorMessage = "Sem filmes disponíveis no momento para a categoria ${MovieCategory.NOW_PLAYING.localLanguageName}."

        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_requestMoviesFromRepository_shouldEmitErrorWithNullMoviesListMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar filmes ${MovieCategory.NOW_PLAYING.localLanguageName}: a lista de filmes está nula."

        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_requestMoviesFromRepository_shouldEmitErrorWithRequestFailedMessage() = runTest {
        val expectedErrorMessage = "Erro ao buscar filmes ${MovieCategory.NOW_PLAYING.localLanguageName}: falha na requisição."

        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_requestMoviesFromRepository_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategoryUseCase_requestMoviesFromRepository_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        val expectedErrorMessage = "Erro desconhecido ao buscar filmes ${MovieCategory.NOW_PLAYING.localLanguageName}, tente mais tarde."

        Mockito.`when`(moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING)).thenReturn(
            flow {
                emit(MoviesResponse.Loading)
                emit(MoviesResponse.Error(expectedErrorMessage))
            }
        )

        // GIVEN & WHEN
        getMoviesByCategoryUseCase(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

}