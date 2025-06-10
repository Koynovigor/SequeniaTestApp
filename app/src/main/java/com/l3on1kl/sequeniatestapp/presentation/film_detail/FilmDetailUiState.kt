package com.l3on1kl.sequeniatestapp.presentation.film_detail

import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity

sealed interface FilmDetailUiState {
    data object Loading : FilmDetailUiState
    data class Success(val film: FilmEntity) : FilmDetailUiState
    data class Error(val throwable: Throwable) : FilmDetailUiState
}