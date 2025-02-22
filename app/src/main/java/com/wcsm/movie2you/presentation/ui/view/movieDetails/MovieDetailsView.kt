package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wcsm.movie2you.presentation.model.UiState
import com.wcsm.movie2you.presentation.ui.components.GetMovieDetailsError
import com.wcsm.movie2you.presentation.ui.components.MoviesContainer
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MovieDetailsView(
    movieDetailsViewModel: MovieDetailsViewModel,
    movieId: Int,
    onSimilarMovieClick: (movieId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val movieDetails by movieDetailsViewModel.movieDetails.collectAsStateWithLifecycle()
    val movieReviews by movieDetailsViewModel.movieReviews.collectAsStateWithLifecycle()
    val similarMovies by movieDetailsViewModel.similarMovies.collectAsStateWithLifecycle()

    val uiState = UiState(
        isLoading = similarMovies.isLoading,
        error = similarMovies.error,
        success = similarMovies.success,
        data = similarMovies
    )

    LaunchedEffect(Unit) {
        movieDetailsViewModel.getAllMovieData(movieId)
    }

    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if(movieDetails.data != null) {
            MovieDetailsTopBanner(movieDetails.data!!) { onBackPressed() }

            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MovieDetailsSynopsisContainer(movieOverview = movieDetails.data!!.overview)

                MovieDetailsReviewsContainer(uiState = movieReviews) {
                    movieDetailsViewModel.getMovieReviews(movieId)
                }

                MoviesContainer(
                    title = "Mais Como Este",
                    movies = uiState.data,
                    onTryRequestAgain = { movieDetailsViewModel.getSimilarMovies(movieId) }
                ) { movieId ->
                    onSimilarMovieClick(movieId)
                }
            }
        } else if(movieDetails.error?.isNotBlank() == true) {
            GetMovieDetailsError(
                errorMessage = movieDetails.error!!
            ) { onBackPressed() }
        } else {
            MovieDetailsSkeleton()
        }
    }
}

@Preview
@Composable
private fun MovieDetailsViewPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MovieDetailsView(hiltViewModel(), movieId = 1, {}) {}
        }
    }
}