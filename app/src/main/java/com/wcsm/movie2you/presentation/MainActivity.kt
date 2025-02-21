package com.wcsm.movie2you.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.presentation.ui.theme.BackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.view.moviesList.MoviesView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        setContent {
            Movie2YouTheme {
                var isSplashScreenVisible by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(2000)
                    isSplashScreenVisible = false
                }

                splashScreen.setKeepOnScreenCondition{ isSplashScreenVisible }

                Column(
                    modifier = Modifier.fillMaxSize().background(BackgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    MoviesView()
                }
            }
        }
    }
}