package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wcsm.movie2you.R
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsContainerColor
import com.wcsm.movie2you.presentation.ui.theme.LightGrayColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.MovieCardShimmerColor
import com.wcsm.movie2you.utils.Constants

@Composable
fun MovieCard(
    movie: Movie?,
    onMovieCardClick: ((movieId: Int) -> Unit)? = null
) {
    val imageSize = Constants.TMDB_POSTER_IMAGE_SIZE
    val imageBaseUrl = Constants.TMDB_MOVIE_IMAGE_BASE_URL
    val posterImageUrl = "$imageBaseUrl$imageSize${movie?.posterPath}"

    var isImageLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .size(width = 150.dp, height = 200.dp)
    ) {
        if(movie?.posterPath?.isBlank() == true) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CommentsContainerColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.no_movie_poster),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(LightGrayColor),
                    modifier = Modifier.size(80.dp)
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Filme indisponível",
                    color = LightGrayColor,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        } else {
            if(movie != null) {
                AsyncImage(
                    model = posterImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onMovieCardClick?.let { onMovieCardClick(movie.id) } },
                    contentScale = ContentScale.Crop,
                    onSuccess = { isImageLoading = false }
                )
            }

            if(isImageLoading) {
                MovieCardSkeleton()
            }
        }
    }
}


@Preview
@Composable
private fun MovieCardPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier.fillMaxWidth().background(AppBackgroundColor)
        ) {
            Text(
                text = "Em Exibição",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MovieCard(Movie(1, "a")) {}

                MovieCard(Movie(2, "b")) {}
            }
        }
    }
}

@Composable
private fun MovieCardSkeleton() {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val localConfiguration = LocalConfiguration.current
    val target = (localConfiguration.screenWidthDp * 4).toFloat()

    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = target,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val skeletonColor = Brush.linearGradient(
        colors = MovieCardShimmerColor,
        end = Offset(x = scale, y = scale)
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .size(width = 150.dp, height = 200.dp)
            .background(skeletonColor, shape = RoundedCornerShape(12.dp))

    )
}

@Preview
@Composable
private fun MovieCardSkeletonPreview() {
    Movie2YouTheme {
        Box(
            modifier = Modifier.size(300.dp).background(AppBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            MovieCardSkeleton()
        }
    }
}

@Preview
@Composable
private fun MovieCardNoPosterPreview() {
    Movie2YouTheme {
        Box(
            modifier = Modifier.size(300.dp).background(AppBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            MovieCard(Movie(1, ""))
        }
    }
}