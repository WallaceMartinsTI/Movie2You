package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.domain.model.MovieDetailsComment
import com.wcsm.movie2you.presentation.ui.theme.CommentsContainerColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsDividerColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsTextColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.TitleTextColor

@Composable
fun MovieDetailsCommentsContainer(
    movieDetailsCommentList: List<MovieDetailsComment>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Comentários",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .height(300.dp)
                .background(CommentsContainerColor)
                .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movieDetailsCommentList) { movieDetailsComment ->
                MovieComment(movieDetailsComment)
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailsCommentsContainerPreview() {
    Movie2YouTheme {
        val comments = listOf(
            MovieDetailsComment(
                name = "Alexandra",
                comment = "It was great. This movie was a continuation of the Avengers of the Eternal War. See it first and then this movie"
            ),
            MovieDetailsComment(
                name = "Jason",
                comment = "The best hero is Iron Man. Not because of his clothes, but because of his personality"
            ),
            MovieDetailsComment(
                name = "Amanda",
                comment = "It was interesting. I think Loki and Stark and Captain America will die soon"
            )
        )

        MovieDetailsCommentsContainer(movieDetailsCommentList = comments)
    }
}

@Composable
private fun MovieComment(
    movieDetailsComment: MovieDetailsComment
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = movieDetailsComment.name,
            color = TitleTextColor,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = movieDetailsComment.comment,
            color = CommentsTextColor,
            style = MaterialTheme.typography.bodySmall,
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
        val comment = MovieDetailsComment(
            name = "Alexandra",
            comment = "It was great. This movie was a continuation of the Avengers of the Eternal War. See it first and then this movie"
        )

        MovieComment(comment)
    }
}