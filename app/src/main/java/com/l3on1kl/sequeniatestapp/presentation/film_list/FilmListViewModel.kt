package com.l3on1kl.sequeniatestapp.presentation.film_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity
import com.l3on1kl.sequeniatestapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FilmListViewModel(
    private val filmRepository: FilmRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FilmListUiState>(FilmListUiState.Loading)
    val uiState: StateFlow<FilmListUiState> = _uiState.asStateFlow()

    private val _allFilms = MutableStateFlow<List<FilmEntity>>(emptyList())
    private val allFilms = _allFilms.asStateFlow()
    private var allGenres: List<String> = emptyList()

    val selectedGenre = MutableStateFlow<String?>(null)

    init {
        loadFilms()
    }

    fun onGenreSelected(genre: String?) {
        selectedGenre.value = genre
    }

    val filteredFilms = selectedGenre
        .combine(allFilms) { genre, films ->
            genre?.let {
                films.filter { it.genres.contains(genre) }
            } ?: films
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private fun loadFilms() {
        viewModelScope.launch {
            try {
                val films = filmRepository.getFilms()
                _allFilms.value = films.sortedBy { it.localizedName }

                allGenres = films.flatMap { it.genres }
                    .distinct()
                    .sorted()

                _uiState.value = FilmListUiState.Content(
                    films = films,
                    genres = allGenres
                )
            } catch (e: Exception) {
                _uiState.value = FilmListUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
