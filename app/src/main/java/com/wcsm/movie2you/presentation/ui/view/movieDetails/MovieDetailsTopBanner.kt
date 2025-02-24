package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wcsm.movie2you.R
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.presentation.ui.components.MovieCard
import com.wcsm.movie2you.presentation.ui.theme.LightGrayColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.TitleTextColor
import com.wcsm.movie2you.utils.Constants

@Composable
fun MovieDetailsTopBanner(
    movieDetails: MovieDetails,
    onBackPressed: () -> Unit
) {
    val moviePosterSize = Constants.TMDB_IMAGES_SIZE
    val moviesImagesBaseUrl = Constants.TMDB_MOVIE_IMAGE_BASE_URL
    val backdropImageUrl = "$moviesImagesBaseUrl$moviePosterSize${movieDetails.backdropPath}"

    val movieGenres = if(movieDetails.genres.size >= 2) {
        "${movieDetails.genres[0]} • ${movieDetails.genres[1]}"
    } else if (movieDetails.genres.size == 1) {
        movieDetails.genres[0]
    } else {
        ""
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .fillMaxWidth()
            .height(320.dp)
    ) {
        AsyncImage(
            model = backdropImageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.27f),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBackPressed() }
                    .padding(16.dp)
                    .size(32.dp),
                tint = TitleTextColor
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = movieDetails.title,
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = movieDetails.runtime,
                        color = TitleTextColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = movieGenres,
                        color = TitleTextColor,
                        fontSize = 13.sp
                    )

                    Row(
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(top = 2.dp, end = 4.dp)
                        )

                        Text(
                            text = "${movieDetails.voteAverage} / 10 Média de Votos",
                            color = LightGrayColor,
                        )
                    }
                }

                MovieCard(
                    movie = Movie(
                        id = movieDetails.id,
                        posterPath = movieDetails.posterPath
                    ),
                    onMovieCardClick = null
                )
            }
        }


    }
}

@Preview
@Composable
private fun MovieDetailsTopBannerPreview() {
    Movie2YouTheme {
        val movieDetails = MovieDetails(
            backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
            genres = listOf("Action", "Super Hero"),
            id = 278,
            overview = "Em 1946, Andy Dufresne, um banqueiro jovem e bem sucedido, tem a sua vida radicalmente modificada ao ser condenado por um crime que nunca cometeu, o homicídio de sua esposa e do amante dela. Ele é mandado para uma prisão que é o pesadelo de qualquer detento, a Penitenciária Estadual de Shawshank, no Maine. Lá ele irá cumprir a pena perpétua. Andy logo será apresentado a Warden Norton, o corrupto e cruel agente penitenciário, que usa a Bíblia como arma de controle e ao Capitão Byron Hadley que trata os internos como animais. Andy faz amizade com Ellis Boyd Redding, um prisioneiro que cumpre pena há 20 anos e controla o mercado negro da instituição.",
            posterPath = "/xSnM4ahmz692msbMTBsfBWHvR3M.jpg",
            runtime = "2 hora(s) e 22 minuto(s)",
            title = "Um Sonho de Liberdade",
            voteAverage = "8.7",
        )

        MovieDetailsTopBanner(movieDetails = movieDetails) {}
    }
}