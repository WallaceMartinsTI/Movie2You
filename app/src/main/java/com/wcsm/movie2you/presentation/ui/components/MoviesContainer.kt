package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.presentation.model.UiState
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.AppIconColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MoviesContainer(
    title: String,
    movies: UiState<List<Movie>>?,
    modifier: Modifier = Modifier,
    onTryRequestAgain: () -> Unit,
    onMovieCardClick: (movieId: Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            movies?.let { moviesState ->
                if(moviesState.isLoading) {
                    Movie2YouCircularLoading(
                        loadingMessage = "Carregando filmes...",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    if(movies.error?.isNotBlank() == true) {
                        NoMovies(message = movies.error) { onTryRequestAgain() }
                    } else if(movies.data.isEmpty()) {
                        NoMovies(message = "Sem filmes no momento") { onTryRequestAgain() }
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(movies.data) { movie ->
                                MovieCard(
                                    movie = movie
                                ) { movieId ->
                                    onMovieCardClick(movieId)
                                }
                            }
                        }
                    }
                }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundColor)
        ) {
            MoviesContainer(
                title = "Em Exibição",
                movies =  UiState(data = movies),
                onTryRequestAgain = {}
            ) {}
        }
    }
}

@Composable
private fun NoMovies(
    message: String,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            color = AppIconColor,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { onTryAgain() },
            colors = ButtonDefaults.buttonColors(
                containerColor = AppIconColor.copy(alpha = 0.7f)
            )
        ) {
            Text(
                text = "Tentar Novamente"
            )
        }
    }
}

@Preview
@Composable
private fun NoMoviesPreview() {
    Movie2YouTheme {
        NoMovies("Sem filmes no momento") { }
    }
}