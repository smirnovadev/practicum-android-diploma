package ru.practicum.android.diploma.filters.domain.models

sealed class FiltersScreenState {
    data class Content(val currentFilters: Filters) : FiltersScreenState()
}
