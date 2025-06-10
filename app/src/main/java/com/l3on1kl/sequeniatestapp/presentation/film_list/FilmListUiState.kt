package com.l3on1kl.sequeniatestapp.presentation.film_list

import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity

sealed class FilmListUiState {
    data object Loading : FilmListUiState()
    data class Content(
        val films: List<FilmEntity>,
        val genres: List<String>
    ) : FilmListUiState()

    data class Error(val message: String) : FilmListUiState()
}
