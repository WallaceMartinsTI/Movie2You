package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.presentation.model.UiState
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MoviesContainer(
    title: String,
    uiState: UiState,
    moviesList: List<Movie>,
    modifier: Modifier = Modifier
) {
    val listToShow = if(uiState.error?.isNotBlank() == true) {
        List(20) { null }
    } else {
        moviesList.ifEmpty { List(20) { null } }
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listToShow) { movie ->
                MovieCard(
                    moviePosterPath = movie?.posterPath ?: ""
                )
            }
        }
    }
}

@Preview
@Composable
private fun MoviesContainerPreview() {
    Movie2YouTheme {
        val movies = listOf(
            Movie(
                adult = false,
                backdropPath = "",
                genreIds = emptyList(),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 5.0,
                posterPath = "/d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 8.5,
                voteCount = 500
            ),
            Movie(
                adult = false,
                backdropPath = "",
                genreIds = emptyList(),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 5.0,
                posterPath = "/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 8.5,
                voteCount = 500
            ),
            Movie(
                adult = false,
                backdropPath = "",
                genreIds = emptyList(),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 5.0,
                posterPath = "/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 8.5,
                voteCount = 500
            ),
            Movie(
                adult = false,
                backdropPath = "",
                genreIds = emptyList(),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 5.0,
                posterPath = "/xVS9XiO9upp2SnWx6KpBYb79hLR.jpg",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 8.5,
                voteCount = 500
            ),
            Movie(
                adult = false,
                backdropPath = "",
                genreIds = emptyList(),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 5.0,
                posterPath = "/4cR3hImKd78dSs652PAkSAyJ5Cx.jpg",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 8.5,
                voteCount = 500
            ),
            Movie(
                adult = false,
                backdropPath = "",
                genreIds = emptyList(),
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 5.0,
                posterPath = "/ttN5D6GKOwKWHmCzDGctAvaNMAi.jpg",
                releaseDate = "",
                title = "",
                video = true,
                voteAverage = 8.5,
                voteCount = 500
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundColor)
        ) {
            MoviesContainer(
                title = "Em Exibição",
                uiState = UiState(),
                moviesList = movies
            )
        }
    }
}