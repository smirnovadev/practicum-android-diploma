package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.favorites.presentation.FavoritesViewModel
import ru.practicum.android.diploma.filters.presentation.CountryViewModel
import ru.practicum.android.diploma.filters.presentation.FiltersViewModel
import ru.practicum.android.diploma.filters.presentation.IndustryViewModel
import ru.practicum.android.diploma.filters.presentation.PlaceToWorkViewModel
import ru.practicum.android.diploma.filters.presentation.RegionViewModel
import ru.practicum.android.diploma.job.ui.viewmodel.JobViewModel
import ru.practicum.android.diploma.search.presentation.SearchViewModel

val viewModelModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel(get(), get())
    }

    viewModel<FavoritesViewModel> {
        FavoritesViewModel(get())
    }

    viewModel<JobViewModel> {
        JobViewModel(get(), get())
    }

    viewModel<FiltersViewModel> {
        FiltersViewModel(get())
    }
    viewModel<PlaceToWorkViewModel> {
        PlaceToWorkViewModel(get())
    }
    viewModel<RegionViewModel> {
        RegionViewModel(get(), get(), get())
    }
    viewModel<CountryViewModel> {
        CountryViewModel(get(), get(), get())
    }
    viewModel<IndustryViewModel> {
        IndustryViewModel(get(), get(), get())
    }
}
