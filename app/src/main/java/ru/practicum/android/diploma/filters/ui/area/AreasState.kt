package ru.practicum.android.diploma.filters.ui.area

import ru.practicum.android.diploma.search.domain.model.fields.Area

sealed interface AreasState {
    data object Loading : AreasState
    data class Content(
        val industryList: List<Area>
    ) : AreasState

    data object Empty : AreasState
    data class Error(val code: Int) : AreasState
}
