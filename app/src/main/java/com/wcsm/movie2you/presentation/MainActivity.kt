package com.wcsm.movie2you.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.SystemUiControllerColor
import com.wcsm.movie2you.presentation.ui.view.movieDetails.MovieDetailsView
import com.wcsm.movie2you.presentation.ui.view.moviesList.MoviesView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        setContent {
            Movie2YouTheme {
                val systemBarsColor = SystemUiControllerColor
                var isSplashScreenVisible by remember { mutableStateOf(true) }

                SetBarColor(systemBarsColor)

                LaunchedEffect(Unit) {
                    delay(2000)
                    isSplashScreenVisible = false
                }

                splashScreen.setKeepOnScreenCondition{ isSplashScreenVisible }

                Column(
                    modifier = Modifier.fillMaxSize().background(AppBackgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //MoviesView { exitApp() }
                    val movieDetails = MovieDetails(
                        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
                        genres = listOf("Action", "Super Hero"),
                        id = 278,
                        overview = "Em 1946, Andy Dufresne, um banqueiro jovem e bem sucedido, tem a sua vida radicalmente modificada ao ser condenado por um crime que nunca cometeu, o homicídio de sua esposa e do amante dela. Ele é mandado para uma prisão que é o pesadelo de qualquer detento, a Penitenciária Estadual de Shawshank, no Maine. Lá ele irá cumprir a pena perpétua. Andy logo será apresentado a Warden Norton, o corrupto e cruel agente penitenciário, que usa a Bíblia como arma de controle e ao Capitão Byron Hadley que trata os internos como animais. Andy faz amizade com Ellis Boyd Redding, um prisioneiro que cumpre pena há 20 anos e controla o mercado negro da instituição.",
                        posterPath = "/xSnM4ahmz692msbMTBsfBWHvR3M.jpg",
                        runtime = 142,
                        title = "Um Sonho de Liberdade",
                        voteAverage = 8.708,
                    )

                    MovieDetailsView(movieDetails)
                }
            }
        }
    }

    @Composable
    fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }

    private fun exitApp() {
        finish()
    }
}