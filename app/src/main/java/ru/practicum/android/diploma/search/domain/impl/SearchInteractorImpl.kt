package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.network.Resource
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.VacancySnippet

class SearchInteractorImpl(
    private val searchRepository: SearchRepository
) : SearchInteractor {
    override fun getVacancies(query: String): Flow<Resource<List<VacancySnippet>>> {
        return searchRepository.getVacancies(query)
    }
}
