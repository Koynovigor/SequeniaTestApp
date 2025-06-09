package com.l3on1kl.sequeniatestapp.domain.model

data class FilmEntity(
    val id: Int,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Float?,
    val imageUrl: String?,
    val description: String?,
    val genres: List<String>
)
