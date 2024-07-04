package ru.practicum.android.diploma.filters.domain.models

sealed class FiltersIconState {
    data object Active : FiltersIconState()
    data object Inactive : FiltersIconState()
}
