package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.network.Resource
import ru.practicum.android.diploma.search.domain.model.VacancySnippet

interface SearchInteractor {
    fun getVacancies(query: String): Flow<Resource<List<VacancySnippet>>>
}