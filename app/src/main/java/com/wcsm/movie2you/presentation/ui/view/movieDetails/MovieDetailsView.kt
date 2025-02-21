package com.wcsm.movie2you.presentation.ui.view.movieDetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wcsm.movie2you.domain.model.Movie
import com.wcsm.movie2you.domain.model.MovieDetails
import com.wcsm.movie2you.domain.model.MovieDetailsComment
import com.wcsm.movie2you.presentation.model.MovieState
import com.wcsm.movie2you.presentation.ui.components.MoviesContainer
import com.wcsm.movie2you.presentation.ui.theme.AppBackgroundColor
import com.wcsm.movie2you.presentation.ui.theme.Movie2YouTheme

@Composable
fun MovieDetailsView(
    movieId: Int,
    onBackPressed: () -> Unit
) {
    val fakeComments = listOf(
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
        ),
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
        ),
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

    val fakeSimilarMovies = listOf(
        Movie(
            id = 1,
            posterPath = "/d8Ryb8AunYAuycVKDp5HpdWPKgC.jpg",
        ),
        Movie(
            id = 2,
            posterPath = "/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
        ),
        Movie(
            id = 3,
            posterPath = "/7iMBZzVZtG0oBug4TfqDb9ZxAOa.jpg",
        ),
        Movie(
            id = 4,
            posterPath = "/xVS9XiO9upp2SnWx6KpBYb79hLR.jpg",
        ),
        Movie(
            id = 5,
            posterPath = "/4cR3hImKd78dSs652PAkSAyJ5Cx.jpg",
        ),
        Movie(
            id = 6,
            posterPath = "/ttN5D6GKOwKWHmCzDGctAvaNMAi.jpg",
        )
    )

    // GET FROM VIEWMODEL
    val movieDetails = MovieDetails(
        backdropPath = "/zfbjgQE1uSd9wiPTX4VzsLi0rGG.jpg",
        genres = listOf("Action", "Super Hero"),
        id = 278,
        overview = "Em 1946, Andy Dufresne, um banqueiro jovem e bem sucedido, tem a sua vida radicalmente modificada ao ser condenado por um crime que nunca cometeu, o homicídio de sua esposa e do amante dela. Ele é mandado para uma prisão que é o pesadelo de qualquer detento, a Penitenciária Estadual de Shawshank, no Maine. Lá ele irá cumprir a pena perpétua. Andy logo será apresentado a Warden Norton, o corrupto e cruel agente penitenciário, que usa a Bíblia como arma de controle e ao Capitão Byron Hadley que trata os internos como animais. Andy faz amizade com Ellis Boyd Redding, um prisioneiro que cumpre pena há 20 anos e controla o mercado negro da instituição.",
        posterPath = "/xSnM4ahmz692msbMTBsfBWHvR3M.jpg",
        runtime = 142,
        title = "Um Sonho de Liberdade",
        voteAverage = 8.708,
    )

    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = Modifier.fillMaxSize()
            //.nestedScroll(nestedScrollConnection)
    ) {
        MovieDetailsTopBanner(movieDetails) { onBackPressed() }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MovieDetailsSynopsisContainer(movieOverview = movieDetails.overview)

            MovieDetailsCommentsContainer(movieDetailsCommentList = fakeComments)

            MoviesContainer(
                title = "Mais Como Este",
                moviesList = fakeSimilarMovies,
                onTryRequestAgain = {}
            ) {}
        }
    }
}

@Preview
@Composable
private fun MovieDetailsViewPreview() {
    Movie2YouTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MovieDetailsView(movieId = 1) {}
        }
    }
}