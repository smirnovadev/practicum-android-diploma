package ru.practicum.android.diploma.job.domain

import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    fun getVacancyById(id: String): Flow<Resource<Vacancy>>
}
