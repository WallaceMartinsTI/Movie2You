package com.wcsm.movie2you.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wcsm.movie2you.R
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.ui.theme.BackgroundColor
import com.wcsm.movie2you.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.ui.theme.TitleTextColor

@Composable
fun MoviesContainer(
    title: String,
    moviesList: List<Movie>,
    modifier: Modifier = Modifier
) {
    val imageSize = "w1280"
    val imageUrl = "https://image.tmdb.org/t/p/$imageSize"

    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = 21.sp,
            color = TitleTextColor,
            //style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(moviesList) { movie ->
                /* PRODUCTION */
                /*AsyncImage(
                    model = "$imageUrl${movie.posterPath}",
                    contentDescription = "${movie.title} poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .size(width = 150.dp, height = 200.dp)
                )*/

                // FOR PREVIEW
                Image(
                    painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .size(width = 150.dp, height = 200.dp)
                        .clickable {  }
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
            modifier = Modifier.fillMaxSize().background(BackgroundColor)
        ) {
            MoviesContainer(
                title = "Em Exibição",
                moviesList = movies
            )
        }
    }
}