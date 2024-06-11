package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.VacancySnippet

interface SearchRepository {
    fun getVacancies(query: String): Flow<List<VacancySnippet>?>
}
