package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersInteractorImpl(private val filtersRepository: FiltersRepository) : FiltersInteractor {
    override suspend fun insertIndustries(industry: Industry) {
        return filtersRepository.insertIndustries(industry)
    }

    override suspend fun deleteIndustry(industry: Industry) {
        return filtersRepository.deleteIndustry(industry)
    }

    override fun getIndustry(): Flow<List<Industry>> {
        return filtersRepository.getIndustry()
    }

    override suspend fun insertAreas(area: Area) {
        return filtersRepository.insertAreas(area)
    }

    override suspend fun deleteArea(area: Area) {
        return filtersRepository.deleteArea(area)
    }

    override fun getArea(): Flow<List<Area>> {
        return filtersRepository.getArea()
    }

}
