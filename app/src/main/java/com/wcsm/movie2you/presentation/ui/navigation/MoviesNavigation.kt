package com.wcsm.movie2you.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.wcsm.movie2you.presentation.model.Screen
import com.wcsm.movie2you.presentation.ui.view.movieDetails.MovieDetailsView
import com.wcsm.movie2you.presentation.ui.view.movieDetails.MovieDetailsViewModel
import com.wcsm.movie2you.presentation.ui.view.moviesList.MoviesView

@Composable
fun MoviesNavigation() {
    val navController = rememberNavController()

    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.MoviesListScreen
    ) {
        composable<Screen.MoviesListScreen> {
            MoviesView { movieId ->
                navController.navigate(Screen.MovieDetailsScreen(movieId))
            }
        }

        composable<Screen.MovieDetailsScreen> {
            val args = it.toRoute<Screen.MovieDetailsScreen>()
            MovieDetailsView(
                movieDetailsViewModel = movieDetailsViewModel,
                movieId = args.movieId
            ) {
                navController.popBackStack()
            }
        }
    }
}