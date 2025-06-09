package com.l3on1kl.sequeniatestapp.domain.repository

import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity

interface FilmRepository {
    suspend fun getFilms(): List<FilmEntity>
}
