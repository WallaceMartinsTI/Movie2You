package com.wcsm.movie2you.presentation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
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

        composable<Screen.MovieDetailsScreen>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val args = it.toRoute<Screen.MovieDetailsScreen>()
            MovieDetailsView(
                movieDetailsViewModel = movieDetailsViewModel,
                movieId = args.movieId,
                onSimilarMovieClick = { movieId ->
                    navController.popBackStack() // TESTAR INVERTER SE NAO FUNCIONAR
                    navController.navigate(Screen.MovieDetailsScreen(movieId))
                }
            ) {
                navController.popBackStack()
            }
        }
    }
}