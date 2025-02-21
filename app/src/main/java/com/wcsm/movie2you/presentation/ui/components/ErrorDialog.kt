package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.AppIconColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsContainerColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.TitleTextColor

@Composable
fun ErrorDialog(
    errorMessage: String,
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(CommentsContainerColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppIconColor)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = "Ícone de erro",
                    tint = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Ícone de fechar",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { onDismiss() }
                        .align(Alignment.TopEnd)
                )
            }

            Text(
                text = "Ooops!",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = errorMessage,
                style = MaterialTheme.typography.titleMedium,
                color = TitleTextColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Button(
                onClick = { onTryAgain() },
                colors = ButtonDefaults.buttonColors(
                    containerColor =  AppBackgroundColor
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Tentar Novamente",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun ErrorDialogPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ErrorDialog(
                errorMessage = "Houve um erro, sem internet no momento.",
                onTryAgain = {},
                onDismiss = {}
            )
        }
    }
}