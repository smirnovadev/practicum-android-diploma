package ru.practicum.android.diploma.job.domain

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed interface JobScreenState {
    data object Loading : JobScreenState

    data object Error : JobScreenState

    data class Content(val vacancy: Vacancy) : JobScreenState
}
