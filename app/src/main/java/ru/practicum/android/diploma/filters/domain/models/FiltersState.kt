package ru.practicum.android.diploma.filters.domain.models

sealed class FiltersState {
    data object Active : FiltersState()
    data object Inactive : FiltersState()
}
