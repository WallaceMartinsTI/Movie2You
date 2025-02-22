package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.MovieCardShimmerColor

@Composable
fun MovieDetailsSkeleton() {
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .fillMaxWidth()
                .height(320.dp)
                .background(skeletonColor, shape = RoundedCornerShape(12.dp))
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(width = 150.dp, height = 30.dp)
                    .background(skeletonColor, shape = RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(skeletonColor, shape = RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(width = 150.dp, height = 30.dp)
                    .background(skeletonColor, shape = RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(skeletonColor, shape = RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(width = 150.dp, height = 30.dp)
                    .background(skeletonColor, shape = RoundedCornerShape(12.dp))
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsSkeletonPreview() {
    Movie2YouTheme {
        MovieDetailsSkeleton()
    }
}