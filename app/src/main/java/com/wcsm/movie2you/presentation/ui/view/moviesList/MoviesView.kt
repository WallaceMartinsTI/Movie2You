package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wcsm.movie2you.R
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.presentation.ui.components.MoviesContainer
import com.wcsm.movie2you.presentation.ui.theme.BackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MoviesView() {
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

    val moviesListViewModel: MoviesListViewModel = hiltViewModel()

    val nowPlayingMovies by moviesListViewModel.nowPlayingMovies.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        moviesListViewModel.getNowPlayingMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.movie2you_app_icon),
                contentDescription = "Ícone do aplicativo Movie2you",
                modifier = Modifier.size(120.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MoviesContainer(
                title = "Em Exibição",
                moviesList = nowPlayingMovies
            )

            MoviesContainer(
                title = "Em Breve",
                moviesList = movies
            )

            MoviesContainer(
                title = "Mais Populares",
                moviesList = movies
            )

            MoviesContainer(
                title = "Melhores Avaliados",
                moviesList = movies
            )
        }
    }
}

@Preview
@Composable
private fun MoviesViewPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(BackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MoviesView()
        }
    }
}