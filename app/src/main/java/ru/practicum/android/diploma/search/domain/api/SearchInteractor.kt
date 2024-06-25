package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancies

interface SearchInteractor {
    fun getVacancies(query: String, page: Int): Flow<Resource<Vacancies>>
}
