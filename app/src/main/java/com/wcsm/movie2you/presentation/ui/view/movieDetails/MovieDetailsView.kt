package com.wcsm.movie2you.presentation.ui.view.movieDetails

import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.presentation.ui.components.GetMovieDetailsError
import com.wcsm.movie2you.presentation.ui.components.MoviesContainer
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.LightGrayColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MovieDetailsView(
    movieId: Int,
    onBackPressed: () -> Unit
) {
    val fakeComments = listOf(
        MovieDetailsReview(
            id = "1",
            userName = "Alexandra",
            comment = "It was great. This movie was a continuation of the Avengers of the Eternal War. See it first and then this movie"
        ),
        MovieDetailsReview(
            id = "2",
            userName = "Jason",
            comment = "The best hero is Iron Man. Not because of his clothes, but because of his personality"
        ),
        MovieDetailsReview(
            id = "3",
            userName = "Amanda",
            comment = "It was interesting. I think Loki and Stark and Captain America will die soon"
        ),
        MovieDetailsReview(
            id = "4",
            userName = "Alexandra",
            comment = "It was great. This movie was a continuation of the Avengers of the Eternal War. See it first and then this movie"
        ),
        MovieDetailsReview(
            id = "5",
            userName = "Jason",
            comment = "The best hero is Iron Man. Not because of his clothes, but because of his personality"
        ),
        MovieDetailsReview(
            id = "6",
            userName = "Amanda",
            comment = "It was interesting. I think Loki and Stark and Captain America will die soon"
        )
    )

    val fakeSimilarMovies = listOf(
        Movie(
            id = 1,
            posterPath = "/d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
        ),
        Movie(
            id = 2,
            posterPath = "/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
        ),
        Movie(
            id = 3,
            posterPath = "/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg",
        ),
        Movie(
            id = 4,
            posterPath = "/xVS9XiO9upp2SnWx6KpBYb79hLR.jpg",
        ),
        Movie(
            id = 5,
            posterPath = "/4cR3hImKd78dSs652PAkSAyJ5Cx.jpg",
        ),
        Movie(
            id = 6,
            posterPath = "/ttN5D6GKOwKWHmCzDGctAvaNMAi.jpg",
        )
    )

    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()

    val movieDetails by movieDetailsViewModel.movieDetails.collectAsStateWithLifecycle()
    val movieReviews by movieDetailsViewModel.movieReviews.collectAsStateWithLifecycle()
    val similarMovies by movieDetailsViewModel.similarMovies.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        movieDetailsViewModel.getMovieDetails(movieId)
        movieDetailsViewModel.getMovieReviews(movieId)
        movieDetailsViewModel.getSimilarMovies(movieId)
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

                MovieDetailsReviewsContainer(uiState = movieReviews)

                MoviesContainer(
                    title = "Mais Como Este",
                    error = similarMovies.error,
                    moviesList = similarMovies.data,
                    onTryRequestAgain = {}
                ) {}
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
            MovieDetailsView(movieId = 1) {}
        }
    }
}