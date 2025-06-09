package com.l3on1kl.sequeniatestapp.domain.repository

import com.l3on1kl.sequeniatestapp.data.remote.ApiService
import com.l3on1kl.sequeniatestapp.domain.model.FilmEntity

class FilmRepositoryImpl(
    private val apiService: ApiService
) : FilmRepository {

    override suspend fun getFilms(): List<FilmEntity> {
        val response = apiService.getFilms()
        return response.films.map { filmDto ->
            FilmEntity(
                id = filmDto.id,
                localizedName = filmDto.localized_name,
                name = filmDto.name,
                year = filmDto.year,
                rating = filmDto.rating,
                imageUrl = filmDto.image_url,
                description = filmDto.description,
                genres = filmDto.genres
            )
        }
    }
}
