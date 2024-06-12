package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.presentation.FavoritesViewModel
import ru.practicum.android.diploma.job.ui.viewModel.JobViewModel
import ru.practicum.android.diploma.search.presentation.SearchViewModel

val viewModelModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel()
    }

    viewModel<FavoritesViewModel> {
        FavoritesViewModel()
    }

    viewModel<JobViewModel> {
        JobViewModel(get())
    }
}
