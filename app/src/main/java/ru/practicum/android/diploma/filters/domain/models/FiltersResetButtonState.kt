package ru.practicum.android.diploma.filters.domain.models

sealed class FiltersResetButtonState {
    data object Visible : FiltersResetButtonState()
    data object InVisible : FiltersResetButtonState()
}
