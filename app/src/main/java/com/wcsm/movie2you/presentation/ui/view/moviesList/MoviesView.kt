package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wcsm.movie2you.R
import com.wcsm.movie2you.domain.model.MovieCategory
import com.wcsm.movie2you.presentation.ui.components.ExitAppDialog
import com.wcsm.movie2you.presentation.ui.components.MoviesContainer
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MoviesView(
    onNavigateToMovieDetails: (movieId: Int) -> Unit
) {
    val moviesListViewModel: MoviesListViewModel = hiltViewModel()

    val localActivity = LocalActivity.current

    val movies by moviesListViewModel.movies.collectAsStateWithLifecycle()

    var showExitAppDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitAppDialog = true
    }

    LaunchedEffect(Unit) {
        moviesListViewModel.getAllMovies()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.movie2you_logo),
                    contentDescription = "Ícone do aplicativo Movie2you",
                    modifier = Modifier.size(80.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MoviesContainer(
                    containerTitle = "Em Exibição",
                    moviesList = movies[MovieCategory.NOW_PLAYING],
                    onTryRequestAgain = { moviesListViewModel.getMoviesByCategory(MovieCategory.NOW_PLAYING) }
                ) { movieId ->
                    onNavigateToMovieDetails(movieId)
                }

                MoviesContainer(
                    containerTitle = "Em Breve",
                    moviesList = movies[MovieCategory.UPCOMING],
                    onTryRequestAgain = { moviesListViewModel.getMoviesByCategory(MovieCategory.UPCOMING) }
                ) { movieId ->
                    onNavigateToMovieDetails(movieId)
                }

                MoviesContainer(
                    containerTitle = "Mais Populares",
                    moviesList = movies[MovieCategory.POPULAR],
                    onTryRequestAgain = { moviesListViewModel.getMoviesByCategory(MovieCategory.POPULAR) }
                ) { movieId ->
                    onNavigateToMovieDetails(movieId)
                }

                MoviesContainer(
                    containerTitle = "Melhores Avaliados",
                    moviesList = movies[MovieCategory.TOP_RATED],
                    onTryRequestAgain = { moviesListViewModel.getMoviesByCategory(MovieCategory.TOP_RATED) }
                ) { movieId ->
                    onNavigateToMovieDetails(movieId)
                }
            }
        }

        if(showExitAppDialog) {
            ExitAppDialog(
                onExitApp = { localActivity?.finish() }
            ) {
                showExitAppDialog = false
            }
        }
    }
}

@Preview
@Composable
private fun MoviesViewPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MoviesView {}
        }
    }
}