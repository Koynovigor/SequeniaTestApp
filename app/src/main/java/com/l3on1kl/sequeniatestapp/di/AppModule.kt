package com.l3on1kl.sequeniatestapp.di

import com.l3on1kl.sequeniatestapp.data.remote.ApiService
import com.l3on1kl.sequeniatestapp.domain.repository.FilmRepository
import com.l3on1kl.sequeniatestapp.domain.repository.FilmRepositoryImpl
import com.l3on1kl.sequeniatestapp.presentation.film_detail.FilmDetailViewModel
import com.l3on1kl.sequeniatestapp.presentation.film_list.FilmListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<FilmRepository> {
        FilmRepositoryImpl(get())
    }

    viewModel {
        FilmListViewModel(get())
    }

    viewModel {
        FilmDetailViewModel(get())
    }
}
