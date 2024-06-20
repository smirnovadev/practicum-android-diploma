package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.data.FavoritesRepositoryImpl
import ru.practicum.android.diploma.favorites.domain.FavoritesRepository
import ru.practicum.android.diploma.filters.data.FiltersRepositoryImpl
import ru.practicum.android.diploma.filters.data.FiltersTransformRepositoryImpl
import ru.practicum.android.diploma.filters.domain.FiltersRepository
import ru.practicum.android.diploma.filters.domain.FiltersTransformRepository
import ru.practicum.android.diploma.job.data.JobRepositoryImpl
import ru.practicum.android.diploma.job.domain.JobRepository
import ru.practicum.android.diploma.search.data.SearchRepositoryImpl
import ru.practicum.android.diploma.search.domain.api.SearchRepository

val repositoryModule = module {
    single<FavoritesRepository> {
        FavoritesRepositoryImpl()
    }

    single<JobRepository> {
        JobRepositoryImpl()
    }

    single<FiltersRepository> {
        FiltersRepositoryImpl(get(), get())
    }

    single<SearchRepository> {
        SearchRepositoryImpl(get(), get())
    }

    single<FiltersTransformRepository> {
        FiltersTransformRepositoryImpl(
            get(), get()
        )
    }
}
