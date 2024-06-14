package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.dto.responses.AreasListResponse
import ru.practicum.android.diploma.data.dto.responses.IndustriesListResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDAO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesListDAO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersInteractorImpl(
    private val filtersRepository: FiltersRepository
) : FiltersInteractor {
    override suspend fun insertIndustry(industry: Industry) {
        return filtersRepository.insertIndustry(industry)
    }

    override suspend fun insertIndustries(industries: List<Industry>) {
        return filtersRepository.insertIndustries(industries)
    }

    override suspend fun deleteIndustry(industry: Industry) {
        return filtersRepository.deleteIndustry(industry)
    }

    override fun getIndustry(): Flow<List<Industry>> {
        return filtersRepository.getIndustry()
    }

    override suspend fun insertArea(area: Area) {
        return filtersRepository.insertArea(area)
    }

    override suspend fun insertAreas(area: List<Area>) {
        return filtersRepository.insertAreas(area)
    }

    override suspend fun deleteArea(area: Area) {
        return filtersRepository.deleteArea(area)
    }

    override fun getArea(): Flow<List<Area>> {
        return filtersRepository.getArea()
    }

    override fun getCountries(): Flow<List<Area>> = filtersRepository.getCountries()

    override fun getRegions(parent: Int): Flow<List<Area>> = filtersRepository.getRegions(parent)

    override suspend fun downloadAreas(): Flow<Pair<AreasListDAO?, Int>> = filtersRepository.downloadAreas().map {
        when (it) {
            is AreasListResponse -> Pair(it.areas, 200)
            else -> Pair(null, it.resultCode)
        }
    }

    override suspend fun downloadIndustries(): Flow<Pair<IndustriesListDAO?, Int>> =
        filtersRepository.downloadIndustries().map {
            when (it) {
                is IndustriesListResponse -> Pair(it.industries, 200)
                else -> Pair(null, it.resultCode)
            }
        }
}
