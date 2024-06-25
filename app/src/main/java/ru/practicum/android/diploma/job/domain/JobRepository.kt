package ru.practicum.android.diploma.job.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy

interface JobRepository {
    fun getVacancyById(id: String): Flow<Resource<Vacancy>>
}
