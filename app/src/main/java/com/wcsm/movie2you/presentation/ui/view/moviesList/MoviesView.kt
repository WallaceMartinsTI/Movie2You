package com.wcsm.movie2you.presentation.ui.view.moviesList

import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wcsm.movie2you.R
import com.wcsm.movie2you.presentation.ui.components.ErrorDialog
import com.wcsm.movie2you.presentation.ui.components.ExitAppDialog
import com.wcsm.movie2you.presentation.ui.components.MoviesContainer
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.AppIconColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MoviesView(
    onExitApp: () -> Unit
) {
    val moviesListViewModel: MoviesListViewModel = hiltViewModel()

    val uiState by moviesListViewModel.uiState.collectAsStateWithLifecycle()
    val nowPlayingMovies by moviesListViewModel.nowPlayingMovies.collectAsStateWithLifecycle()
    val upcomingMovies by moviesListViewModel.upcomingMovies.collectAsStateWithLifecycle()
    val popularMovies by moviesListViewModel.popularMovies.collectAsStateWithLifecycle()
    val topRatedMovies by moviesListViewModel.topRatedMovies.collectAsStateWithLifecycle()

    var showErrorMessage by remember { mutableStateOf(false) }
    var showExitAppDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        moviesListViewModel.getAllMovies()
    }

    LaunchedEffect(uiState) {
        if (uiState.error?.isNotEmpty() == true) {
            showErrorMessage = true
        }

        if(uiState.success) {
            moviesListViewModel.resetUiState()
        }
    }

    BackHandler {
        showExitAppDialog = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                    uiState = uiState,
                    moviesList = nowPlayingMovies
                )

                MoviesContainer(
                    title = "Em Breve",
                    uiState = uiState,
                    moviesList = upcomingMovies
                )

                MoviesContainer(
                    title = "Mais Populares",
                    uiState = uiState,
                    moviesList = popularMovies
                )

                MoviesContainer(
                    title = "Melhores Avaliados",
                    uiState = uiState,
                    moviesList = topRatedMovies
                )
            }
        }

        if (uiState.error?.isNotBlank() == true) {
            if(showErrorMessage) {
                ErrorDialog(
                    errorMessage = uiState.error!!,
                    onTryAgain = {
                        moviesListViewModel.getAllMovies()
                    }
                ) {
                    showErrorMessage = false
                }
            }

            FloatingActionButton(
                onClick = { showErrorMessage = true },
                containerColor = AppIconColor,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = null
                )
            }
        }

        if(showExitAppDialog) {
            ExitAppDialog(
                onExitApp = { onExitApp() }
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
            MoviesView() {}
        }
    }
}