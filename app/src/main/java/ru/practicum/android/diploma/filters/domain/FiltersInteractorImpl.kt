package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.dto.responses.AreasListResponse
import ru.practicum.android.diploma.data.dto.responses.IndustriesListResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDAO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesListDTO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersInteractorImpl(
    private val filtersRepository: FiltersRepository
) : FiltersInteractor {
    override suspend fun insertIndustries(industries: List<Industry>) {
        return filtersRepository.insertIndustries(industries)
    }

    override fun getIndustry(): Flow<List<Industry>> {
        return filtersRepository.getIndustry()
    }

    override fun findIndustry(name: String): Flow<List<Industry>> {
        return filtersRepository.findIndustry(name)
    }

    override suspend fun insertAreas(area: List<Area>) {
        return filtersRepository.insertAreas(area)
    }

    override fun getCountries(): Flow<List<Area>> = filtersRepository.getCountries()

    override fun getRegions(parent: Int): Flow<List<Area>> = filtersRepository.getRegions(parent)

    override fun getRegion(name: String, parent: Int): Flow<List<Area>> = filtersRepository.getRegion(name, parent)

    override suspend fun downloadAreas(): Flow<Pair<AreasListDAO?, Int>> = filtersRepository.downloadAreas().map {
        when (it) {
            is AreasListResponse -> Pair(it.areas, STATUS_OK)
            else -> Pair(null, it.resultCode)
        }
    }

    override suspend fun downloadIndustries(): Flow<Pair<IndustriesListDTO?, Int>> =
        filtersRepository.downloadIndustries().map {
            when (it) {
                is IndustriesListResponse -> Pair(it.industries, STATUS_OK)
                else -> Pair(null, it.resultCode)
            }
        }

    companion object {
        const val STATUS_OK = 200
    }
}
