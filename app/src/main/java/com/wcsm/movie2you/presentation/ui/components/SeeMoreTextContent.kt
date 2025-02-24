package com.wcsm.movie2you.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsContainerColor
import com.wcsm.movie2you.presentation.ui.theme.CommentsTextColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme
import com.wcsm.movie2you.presentation.ui.theme.TitleTextColor

@Composable
fun SeeMoreTextContent(
    fullText: String,
    maxLines: Int,
    modifier: Modifier = Modifier
) {
    var isToShowFullText by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = fullText,
            color = TitleTextColor,
            style = MaterialTheme.typography.bodySmall,
            maxLines = if(isToShowFullText) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .width(85.dp)
                .border(1.dp, CommentsTextColor, RoundedCornerShape(12.dp))
                .background(CommentsContainerColor)
                .padding(vertical = 2.dp, horizontal = 8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { isToShowFullText = !isToShowFullText },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if(isToShowFullText) "Ver menos" else "Ver mais",
                color = CommentsTextColor,
                style = MaterialTheme.typography.bodySmall
            )

            Icon(
                imageVector = if(isToShowFullText) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = CommentsTextColor
            )
        }
    }
}

@Preview
@Composable
private fun SeeMoreTextContentPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SeeMoreTextContent(
                fullText = "Em 1946, Andy Dufresne, um banqueiro jovem e bem sucedido, tem a sua vida radicalmente modificada ao ser condenado por um crime que nunca cometeu, o homicídio de sua esposa e do amante dela. Ele é mandado para uma prisão que é o pesadelo de qualquer detento, a Penitenciária Estadual de Shawshank, no Maine. Lá ele irá cumprir a pena perpétua. Andy logo será apresentado a Warden Norton, o corrupto e cruel agente penitenciário, que usa a Bíblia como arma de controle e ao Capitão Byron Hadley que trata os internos como animais. Andy faz amizade com Ellis Boyd Redding, um prisioneiro que cumpre pena há 20 anos e controla o mercado negro da instituição.",
                maxLines = 3,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}