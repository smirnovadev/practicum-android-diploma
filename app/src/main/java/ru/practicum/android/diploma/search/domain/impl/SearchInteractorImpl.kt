package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filters.domain.models.FiltersParameters
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancies

class SearchInteractorImpl(
    private val searchRepository: SearchRepository
) : SearchInteractor {
    override fun getVacancies(
        query: String,
        page: Int,
        filtersParameters: FiltersParameters
    ): Flow<Resource<Vacancies>> {
        return searchRepository
            .getVacancies(
                query,
                page,
                filtersParameters
            )
    }
}
