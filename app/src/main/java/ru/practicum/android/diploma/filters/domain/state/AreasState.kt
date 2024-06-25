package ru.practicum.android.diploma.filters.domain.state

import ru.practicum.android.diploma.search.domain.model.fields.Area

sealed interface AreasState {
    data object Loading : AreasState
    data class Content(
        val areasList: List<Area>
    ) : AreasState

    data object Empty : AreasState
    data object Error : AreasState
}
