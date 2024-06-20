package ru.practicum.android.diploma.filters.domain.state

import ru.practicum.android.diploma.search.domain.model.fields.Industry

sealed interface IndustryState {
    data object Loading : IndustryState

    data class Content(
        val industryList: List<Industry>
    ) : IndustryState

    data object Empty : IndustryState
    data class Error(val code: Int) : IndustryState
}
