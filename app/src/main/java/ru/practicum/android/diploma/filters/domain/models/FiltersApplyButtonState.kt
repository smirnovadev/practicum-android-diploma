package ru.practicum.android.diploma.filters.domain.models

sealed class FiltersApplyButtonState {
    data object Visible : FiltersApplyButtonState()
    data object InVisible : FiltersApplyButtonState()
}
