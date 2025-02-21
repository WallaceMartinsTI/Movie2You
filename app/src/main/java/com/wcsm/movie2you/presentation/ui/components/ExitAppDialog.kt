package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.AppIconColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsContainerColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun ExitAppDialog(
    onExitApp: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(CommentsContainerColor)
                .width(250.dp)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                tint = AppIconColor,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = "Movie2You",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
            )

            Text(
                text = "Deseja sair do app?",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppBackgroundColor
                ),
                modifier = Modifier.width(180.dp)
            ) {
                Text(
                    text = "Continuar no App",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Button(
                onClick = { onExitApp() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppIconColor.copy(alpha = 0.7f)
                ),
                modifier = Modifier.width(180.dp)
            ) {
                Text(
                    text = "Sair do App",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview
@Composable
private fun ExitAppDialogPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(AppBackgroundColor)
        ) {
            ExitAppDialog({}) { }
        }
    }
}