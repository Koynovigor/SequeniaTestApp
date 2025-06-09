package com.l3on1kl.sequeniatestapp.data.remote.model

data class FilmsResponseDto(
    val films: List<FilmDto>
)

data class FilmDto(
    val id: Int,
    val localized_name: String,
    val name: String,
    val year: Int,
    val rating: Float?,
    val image_url: String?,
    val description: String?,
    val genres: List<String>
)
