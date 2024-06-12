package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.impl.JobInteractorImpl
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.impl.SearchInteractorImpl

val interactorModule = module {
    single<SearchInteractor> {
        SearchInteractorImpl(get())
    }

    single<JobInteractor> {
        JobInteractorImpl(get())
    }
}
