package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.domain.model.MovieDetailsReview
import com.wcsm.movie2you.presentation.model.UiState
import com.wcsm.movie2you.presentation.ui.components.Movie2YouCircularLoading
import com.wcsm.movie2you.presentation.ui.theme.AppIconColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsContainerColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsDividerColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsTextColor
import com.wcsm.movie2you.presentation.ui.theme.LightGrayColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.TitleTextColor

@Composable
fun MovieDetailsReviewsContainer(
    uiState: UiState<List<MovieDetailsReview>?>,
    onTryRequestAgain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Comentários",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .height(300.dp)
                .background(CommentsContainerColor)
                .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
                .padding(16.dp),
        ) {
            if(uiState.isLoading) {
                Movie2YouCircularLoading(
                    loadingMessage = "Carregando comentários...",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                if(!uiState.data.isNullOrEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.data) { movieDetailsComment ->
                            MovieComment(movieDetailsComment)
                        }
                    }
                }

                if(uiState.error?.isNotBlank() == true){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.error,
                            color = LightGrayColor,
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = { onTryRequestAgain() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppIconColor.copy(alpha = 0.7f)
                            )
                        ) {
                            Text(
                                text = "Tentar Novamente"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailsReviewsContainerPreview() {
    Movie2YouTheme {
        val comments = listOf(
            MovieDetailsReview(
                id = "1",
                userName = "Alexandra",
                review = "It was great. This movie was a continuation of the Avengers of the Eternal War. See it first and then this movie"
            ),
            MovieDetailsReview(
                id = "2",
                userName = "Jason",
                review = "The best hero is Iron Man. Not because of his clothes, but because of his personality"
            ),
            MovieDetailsReview(
                id = "3",
                userName = "Amanda",
                review = "It was interesting. I think Loki and Stark and Captain America will die soon"
            )
        )

        val uiState = UiState<List<MovieDetailsReview>?>(data = comments)
        MovieDetailsReviewsContainer(uiState = uiState) {}
    }
}

@Composable
private fun MovieComment(
    movieDetailsReview: MovieDetailsReview
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = movieDetailsReview.userName,
            color = TitleTextColor,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = movieDetailsReview.review,
            color = CommentsTextColor,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        HorizontalDivider(
            color = CommentsDividerColor
        )
    }
}

@Preview
@Composable
private fun MovieCommentPreview() {
    Movie2YouTheme {
        val comment = MovieDetailsReview(
            id = "1",
            userName = "Alexandra",
            review = "It was great. This movie was a continuation of the Avengers of the Eternal War. See it first and then this movie"
        )

        MovieComment(comment)
    }
}