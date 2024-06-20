package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancies

interface SearchRepository {
    fun getVacancies(
        query: String,
        page: Int,
        salary: Int?,
        salaryFlag: Boolean,
        industry: String?,
        area: String?
    ): Flow<Resource<Vacancies>>
}
