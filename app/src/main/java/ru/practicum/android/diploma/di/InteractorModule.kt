package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.domain.FavoritesInteractor
import ru.practicum.android.diploma.favorites.domain.FavoritesInteractorImpl
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.domain.FiltersInteractorImpl
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractorImpl
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractorSave
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractorSaveImpl
import ru.practicum.android.diploma.filters.domain.FiltersTransformInteractor
import ru.practicum.android.diploma.filters.domain.FiltersTransformInteractorImpl
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.impl.JobInteractorImpl
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.impl.SearchInteractorImpl

val interactorModule = module {
    single<SearchInteractor> {
        SearchInteractorImpl(get())
    }

    single<FavoritesInteractor> {
        FavoritesInteractorImpl(get())
    }

    single<JobInteractor> {
        JobInteractorImpl(get(), get(), get())
    }

    single<FiltersInteractor> {
        FiltersInteractorImpl(get())
    }

    single<FiltersSharedInteractor> {
        FiltersSharedInteractorImpl(get(), get())
    }

    single<FiltersTransformInteractor> {
        FiltersTransformInteractorImpl(get())
    }

    single<FiltersSharedInteractorSave> {
        FiltersSharedInteractorSaveImpl(get())
    }
}
