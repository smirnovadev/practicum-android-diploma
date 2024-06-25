package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filters.domain.models.FiltersParameters
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancies

interface SearchRepository {
    fun getVacancies(
        query: String,
        page: Int,
        filtersParameters: FiltersParameters
    ): Flow<Resource<Vacancies>>
}
