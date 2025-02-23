package com.wcsm.movie2you.domain.model

enum class MovieCategory(val localLanguageName: String) {
    NOW_PLAYING("Em Exibição"),
    UPCOMING("Em Breve"),
    POPULAR("Mais Populares"),
    TOP_RATED("Melhores Avaliados")
}