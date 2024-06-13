package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancies

interface SearchRepository {
    fun getVacancies(query: String): Flow<Resource<Vacancies>>
}
