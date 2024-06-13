package ru.practicum.android.diploma.job.domain

import ru.practicum.android.diploma.data.dto.responses.fields.SearchVacanciesUnit

sealed interface JobScreenState {
    data object Loading : JobScreenState

    data object Error : JobScreenState

    data class Content(val vacancies: SearchVacanciesUnit) : JobScreenState // дописать
}
