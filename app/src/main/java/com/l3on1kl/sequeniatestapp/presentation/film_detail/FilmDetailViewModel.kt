package com.l3on1kl.sequeniatestapp.presentation.film_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l3on1kl.sequeniatestapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val repository: FilmRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FilmDetailUiState>(FilmDetailUiState.Loading)
    val uiState: StateFlow<FilmDetailUiState> = _uiState.asStateFlow()

    fun loadFilm(id: Int) {
        viewModelScope.launch {
            _uiState.value = FilmDetailUiState.Loading
            try {
                val film = repository.getFilmById(id)
                _uiState.value = FilmDetailUiState.Success(film)
            } catch (e: Exception) {
                _uiState.value = FilmDetailUiState.Error(e)
            }
        }
    }
}