package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.data.FavoritesRepositoryImpl
import ru.practicum.android.diploma.favorites.domain.FavoritesRepository
import ru.practicum.android.diploma.filters.data.FiltersRepositoryImpl
import ru.practicum.android.diploma.filters.domain.FiltersRepository
import ru.practicum.android.diploma.job.data.JobRepositoryImpl
import ru.practicum.android.diploma.job.domain.JobRepository
import ru.practicum.android.diploma.search.data.SearchRepositoryImpl

val repositoryModule = module {
    single<FavoritesRepository> {
        FavoritesRepositoryImpl()
    }

    single<JobRepository> {
        JobRepositoryImpl()
    }

    single<FiltersRepository> {
        FiltersRepositoryImpl()
    }

    single<SearchRepositoryImpl> {
        SearchRepositoryImpl()
    }
}
