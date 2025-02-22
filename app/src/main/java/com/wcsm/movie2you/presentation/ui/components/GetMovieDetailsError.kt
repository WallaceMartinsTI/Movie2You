package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.R
import com.wcsm.movie2you.presentation.ui.theme.LightGrayColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.TitleTextColor

@Composable
fun GetMovieDetailsError(
    errorMessage: String,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
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
        }

        Spacer(Modifier.height(24.dp))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = LightGrayColor,
            modifier = Modifier.size(80.dp)
        )

        Text(
            text = "Ooops!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = errorMessage,
            color = LightGrayColor,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun GetMovieDetailsErrorPreview() {
    Movie2YouTheme {
        val message = "Erro ao buscar detalhes deste filme: falha na requisição"
        GetMovieDetailsError(message) {}
    }
}