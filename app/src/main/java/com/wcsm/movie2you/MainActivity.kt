package com.wcsm.movie2you

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.wcsm.movie2you.data.remote.api.TMDBAPIService
import com.wcsm.movie2you.ui.theme.Movie2YouTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("HELLO WORLD")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("#-# TESTE #-#", "onStart() - vai chamar")
        getNowPLayingMoviews()
    }

    private fun getNowPLayingMoviews() {
        Log.i("#-# TESTE #-#", "CHAMOU: getNowPLayingMoviews")
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val retrofitService = retrofit.create(TMDBAPIService::class.java)

        runBlocking {
            val movies = retrofitService.getNowPlayingMovies()
            Log.i("#-# TESTE #-#", "movies: $movies")
            val moviesResults = movies.body()?.results
            Log.i("#-# TESTE #-#", "moviesBody: $moviesResults")
        }
    }

    class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            val request = requestBuilder.addHeader(
                "accept", "application/json"
            ).addHeader(
                "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODcyNzgyYTY0MjIwMzI3ODMwN2EyOTk4NTBiODYzZCIsIm5iZiI6MTY1MzY4ODM1NC43OCwic3ViIjoiNjI5MTQ4MjJmYjgzNDYwMDY2NmYzYTFjIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.k4kXEJK6o8vXIDAQi4xX3627jiU56-jC9lAsHrIP6rU"
            ).build()

            return chain.proceed(request)
        }

    }
}