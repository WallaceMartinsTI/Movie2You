package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.presentation.ui.theme.AppIconColor
import com.wcsm.movie2you.presentation.ui.theme.LightGrayColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun Movie2YouCircularLoading(
    loadingMessage: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = AppIconColor
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = loadingMessage,
            color = LightGrayColor
        )
    }
}

@Preview
@Composable
private fun Movie2YouCircularLoadingPreview() {
    Movie2YouTheme {
        Movie2YouCircularLoading("Carregando filmes...")
    }
}