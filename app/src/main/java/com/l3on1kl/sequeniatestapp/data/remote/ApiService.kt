package com.l3on1kl.sequeniatestapp.data.remote

import com.l3on1kl.sequeniatestapp.data.remote.model.FilmsResponseDto
import retrofit2.http.GET

interface ApiService {
    @GET("films.json")
    suspend fun getFilms(): FilmsResponseDto
}
