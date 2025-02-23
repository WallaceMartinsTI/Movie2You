package com.wcsm.movie2you.data.remote.api.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.data.remote.api.dto.getMovies.MoviesDates
import com.wcsm.movie2you.data.remote.api.dto.getMovies.MoviesResponseDTO
import com.wcsm.movie2you.data.remote.api.dto.getMovies.MovieResult
import com.wcsm.movie2you.data.remote.api.dto.movieDetails.MovieDetailsResponseDTO
import com.wcsm.movie2you.data.remote.api.dto.movieReviews.MovieReviewsAuthorDetails
import com.wcsm.movie2you.data.remote.api.dto.movieReviews.MovieReviewsDTO
import com.wcsm.movie2you.data.remote.api.dto.movieReviews.MovieReviewsResult
import com.wcsm.movie2you.domain.model.MovieCategory
import com.wcsm.movie2you.domain.model.MoviesResponse
import com.wcsm.movie2you.domain.repository.MoviesRepository
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryImplTest {

    private val fakeMoviesResponseDTO = MoviesResponseDTO(
        moviesDates = MoviesDates(maximum = "", minimum = ""),
        page = 1,
        movieResults = listOf(
            MovieResult(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 1.0,
                posterPath = "poster1",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 1.0,
                voteCount = 1
            ),
            MovieResult(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2),
                id = 2,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 1.0,
                posterPath = "poster2",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 1.0,
                voteCount = 1
            ),
            MovieResult(
                adult = false,
                backdropPath = "",
                genreIds = listOf(1, 2),
                id = 3,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 1.0,
                posterPath = "poster3",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 1.0,
                voteCount = 1
            )
        ),
        totalPages = 1,
        totalResults = 1
    )

    private val fakeMovieReviewsResponseDTO = MovieReviewsDTO(
        id = 1,
        page = 1,
        movieReviewsResults = listOf(
            MovieReviewsResult(
                author = "",
                movieReviewsAuthorDetails = MovieReviewsAuthorDetails(
                    avatarPath = "",
                    name = "",
                    rating = 1.0,
                    username = "Reviwer1"
                ),
                content = "Review1",
                createdAt = "",
                id = "1",
                updatedAt = "",
                url = ""
            )
        ),
        totalPages = 1,
        totalResults = 1
    )

    @Mock
    private lateinit var tmdbApiService: TMDBAPIService

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        moviesRepository = MoviesRepositoryImpl(tmdbApiService)
    }

    @Test
    fun getMoviesByCategory_getNowPlayingMoviesFromAPI_shouldEmitSuccessWithMoviesResultList() = runTest {
        Mockito.`when`(tmdbApiService.getNowPlayingMovies(anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO)
        )

        val expectedResponse = fakeMoviesResponseDTO.movieResults?.map { it.toMovie() }

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_getUpcomingMoviesFromAPI_shouldEmitSuccessWithMoviesResultList() = runTest {
        Mockito.`when`(tmdbApiService.getUpcomingMovies(anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO)
        )

        val expectedResponse = fakeMoviesResponseDTO.movieResults?.map { it.toMovie() }

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.UPCOMING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_getPopularMoviesFromAPI_shouldEmitSuccessWithMoviesResultList() = runTest {
        Mockito.`when`(tmdbApiService.getPopularMovies(anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO)
        )

        val expectedResponse = fakeMoviesResponseDTO.movieResults?.map { it.toMovie() }

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.POPULAR).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_getTopRatedMoviesFromAPI_shouldEmitSuccessWithMoviesResultList() = runTest {
        Mockito.`when`(tmdbApiService.getTopRatedMovies(anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO)
        )

        val expectedResponse = fakeMoviesResponseDTO.movieResults?.map { it.toMovie() }

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.TOP_RATED).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }


    // getMoviesByCategory errors will be tested with the NowPlaying movie type

    @Test
    fun getMoviesByCategory_requestMoviesFromAPI_shouldEmitErrorWithAnEmptyListAndNoAvailableMoviesForThisCategoryMessage() = runTest {
        Mockito.`when`(tmdbApiService.getNowPlayingMovies(anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO.copy(movieResults = emptyList()))
        )

        val expectedErrorMessage = "Sem filmes disponíveis no momento para a categoria ${MovieCategory.NOW_PLAYING.localLanguageName}."

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_requestMoviesFromAPI_shouldEmitErrorWithNullMoviesListMessage() = runTest {
        Mockito.`when`(tmdbApiService.getNowPlayingMovies(anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO.copy(movieResults = null))
        )

        val expectedErrorMessage = "Erro ao buscar filmes ${MovieCategory.NOW_PLAYING.localLanguageName}: a lista de filmes está nula."

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_requestMoviesFromAPI_shouldEmitErrorWithRequestFailedMessage() = runTest {
        Mockito.`when`(tmdbApiService.getNowPlayingMovies(anyString())).thenReturn(
            Response.error(
                500,
                "{ \"message\": \"Internal Server Error\" }".toResponseBody("application/json".toMediaType())
            )
        )

        val expectedErrorMessage = "Erro ao buscar filmes ${MovieCategory.NOW_PLAYING.localLanguageName}: falha na requisição."

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_requestMoviesFromAPI_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        Mockito.`when`(tmdbApiService.getNowPlayingMovies(anyString())).thenAnswer {
            throw UnknownHostException()
        }

        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviesByCategory_requestMoviesFromAPI_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        Mockito.`when`(tmdbApiService.getNowPlayingMovies(anyString())).thenAnswer {
            throw Exception("unknown error")
        }

        val expectedErrorMessage = "Erro desconhecido ao buscar filmes ${MovieCategory.NOW_PLAYING.localLanguageName}, tente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getMoviesByCategory(MovieCategory.NOW_PLAYING).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetails_requestMovieDetailsFromAPI_shouldEmitSuccessWithMovieDetails() = runTest {
        val fakeMovieDetailsResponseDTO = MovieDetailsResponseDTO(
            adult = false,
            backdropPath = "backdropPath1",
            belongsToCollection = "",
            budget = 1,
            movieGenres = emptyList(),
            homepage = "",
            id = 1,
            imdbId = "",
            originCountry = emptyList(),
            originalLanguage = "",
            originalTitle = "",
            overview = "overview1",
            popularity = 1.0,
            posterPath = "posterPath1",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            releaseDate = "",
            revenue = 1,
            runtime = 1,
            spokenLanguages = emptyList(),
            status = "",
            tagline = "",
            title = "movieTitle1",
            video = false,
            voteAverage = 1.0,
            voteCount = 1
        )

        Mockito.`when`(tmdbApiService.getMovieDetails(anyInt(), anyString())).thenReturn(
            Response.success(fakeMovieDetailsResponseDTO)
        )

        val expectedResponse = fakeMovieDetailsResponseDTO.toMovieDetails()

        // GIVEN & WHEN
        moviesRepository.getMovieDetails(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetails_requestMovieDetailsFromAPI_shouldEmitErrorWithRequestFailedMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieDetails(anyInt(), anyString())).thenReturn(
            Response.success(null)
        )

        val expectedErrorMessage = "Erro ao buscar detalhes deste filme: falha na requisição."

        // GIVEN & WHEN
        moviesRepository.getMovieDetails(-1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetails_requestMovieDetailsFromAPI_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieDetails(anyInt(), anyString())).thenAnswer {
            throw UnknownHostException()
        }

        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getMovieDetails(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieDetails_requestMovieDetailsFromAPI_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieDetails(anyInt(), anyString())).thenAnswer {
            throw Exception("unknown error")
        }

        val expectedErrorMessage = "Erro desconhecido ao buscar detalhes deste filme, favor reportar o erro."

        // GIVEN & WHEN
        moviesRepository.getMovieDetails(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviews_requestMovieReviewsFromAPI_shouldEmitSuccessWithMovieReviews() = runTest {
        Mockito.`when`(tmdbApiService.getMovieReviews(anyInt(), anyString())).thenReturn(
            Response.success(fakeMovieReviewsResponseDTO)
        )

        val expectedResponse = fakeMovieReviewsResponseDTO.movieReviewsResults?.map { it.toMovieDetailsComment() }

        // GIVEN & WHEN
        moviesRepository.getMovieReviews(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviews_requestMovieReviewsFromAPI_shouldEmitErrorWithAnEmptyListAndNoAvailableReviewsForThisMovieMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieReviews(anyInt(), anyString())).thenReturn(
            Response.success(fakeMovieReviewsResponseDTO.copy(movieReviewsResults = emptyList()))
        )

        val expectedErrorMessage = "Sem comentários disponíveis para este filme."

        // GIVEN & WHEN
        moviesRepository.getMovieReviews(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviews_requestMovieReviewsFromAPI_shouldEmitErrorWithNullReviewsListMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieReviews(anyInt(), anyString())).thenReturn(
            Response.success(fakeMovieReviewsResponseDTO.copy(movieReviewsResults = null))
        )

        val expectedErrorMessage = "Erro ao buscar comentários deste filme: a lista de comentários está nula."

        // GIVEN & WHEN
        moviesRepository.getMovieReviews(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviews_requestMovieReviewsFromAPI_shouldEmitErrorWithRequestFailedMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieReviews(anyInt(), anyString())).thenReturn(
            Response.error(
                500,
                "{ \"message\": \"Internal Server Error\" }".toResponseBody("application/json".toMediaType())
            )
        )

        val expectedErrorMessage = "Erro ao buscar comentários deste filme: falha na requisição."

        // GIVEN & WHEN
        moviesRepository.getMovieReviews(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviews_requestMovieReviewsFromAPI_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieReviews(anyInt(), anyString())).thenAnswer {
            throw UnknownHostException()
        }

        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getMovieReviews(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMovieReviews_requestMovieReviewsFromAPI_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        Mockito.`when`(tmdbApiService.getMovieReviews(anyInt(), anyString())).thenAnswer {
            throw Exception("unknown error")
        }

        val expectedErrorMessage = "Erro desconhecido ao buscar comentários deste filme, tente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getMovieReviews(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMovies_getSimilarMoviesFromAPI_shouldEmitSuccessWithSimilarMoviesResultList() = runTest {
        Mockito.`when`(tmdbApiService.getSimilarMovies(anyInt(), anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO)
        )

        val expectedResponse = fakeMoviesResponseDTO.movieResults?.map { it.toMovie() }

        // GIVEN & WHEN
        moviesRepository.getSimilarMovies(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Success).data).isEqualTo(expectedResponse)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMovies_getSimilarMoviesFromAPI_shouldEmitErrorWithAnEmptyListAndNoAvailableSimilarMoviesForThisMovieMessage() = runTest {
        Mockito.`when`(tmdbApiService.getSimilarMovies(anyInt(), anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO.copy(movieResults = emptyList()))
        )

        val expectedErrorMessage = "Sem filmes similares no momento."

        // GIVEN & WHEN
        moviesRepository.getSimilarMovies(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMovies_getSimilarMoviesFromAPI_shouldEmitErrorWithNullSimilarMoviesListMessage() = runTest {
        Mockito.`when`(tmdbApiService.getSimilarMovies(anyInt(), anyString())).thenReturn(
            Response.success(fakeMoviesResponseDTO.copy(movieResults = null))
        )

        val expectedErrorMessage = "Erro ao buscar filmes similares: a lista de filmes está nula."

        // GIVEN & WHEN
        moviesRepository.getSimilarMovies(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMovies_getSimilarMoviesFromAPI_shouldEmitErrorWithRequestFailedMessage() = runTest {
        Mockito.`when`(tmdbApiService.getSimilarMovies(anyInt(), anyString())).thenReturn(
            Response.error(
                500,
                "{ \"message\": \"Internal Server Error\" }".toResponseBody("application/json".toMediaType())
            )
        )

        val expectedErrorMessage = "Erro ao buscar filmes similares: falha na requisição."

        // GIVEN & WHEN
        moviesRepository.getSimilarMovies(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMovies_getSimilarMoviesFromAPI_shouldEmitErrorWithNoInternetConnectionMessage() = runTest {
        Mockito.`when`(tmdbApiService.getSimilarMovies(anyInt(), anyString())).thenAnswer {
            throw UnknownHostException()
        }

        val expectedErrorMessage = "Sem conexão com a internet, tente novamente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getSimilarMovies(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getSimilarMovies_getSimilarMoviesFromAPI_shouldEmitErrorWithUnknownErrorMessage() = runTest {
        Mockito.`when`(tmdbApiService.getSimilarMovies(anyInt(), anyString())).thenAnswer {
            throw Exception("unknown error")
        }

        val expectedErrorMessage = "Erro desconhecido ao buscar filmes similares, tente mais tarde."

        // GIVEN & WHEN
        moviesRepository.getSimilarMovies(1).test {
            // THEN
            assertThat(awaitItem()).isInstanceOf(MoviesResponse.Loading::class.java)

            assertThat((awaitItem() as MoviesResponse.Error).errorMessage).isEqualTo(expectedErrorMessage)

            // Important: Cancels the flow to prevent coroutine leaks in the test
            cancelAndIgnoreRemainingEvents()
        }
    }

}