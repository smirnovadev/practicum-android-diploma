package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.dto.responses.AreasListResponse
import ru.practicum.android.diploma.data.dto.responses.IndustriesListResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDTO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesListDAO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersInteractorImpl(
    private val filtersRepository: FiltersRepository
) : FiltersInteractor {
    override suspend fun insertIndustries(industries: List<Industry>) {
        return filtersRepository.insertIndustries(industries)
    }

    override suspend fun getIndustry(): Flow<List<Industry>> {
        return filtersRepository.getIndustry()
    }

    override suspend fun findIndustry(name: String): Flow<List<Industry>> {
        return filtersRepository.findIndustry(name)
    }

    override suspend fun insertAreas(area: List<Area>) {
        return filtersRepository.insertAreas(area)
    }

    override suspend fun getCountries(): Flow<List<Area>> {
        return filtersRepository.getCountries()
    }

    override suspend fun getRegionsByParent(parent: String?): Flow<List<Area>> {
        return filtersRepository.getRegionsByParent(parent)
    }

    override suspend fun getAllRegions(): Flow<List<Area>> {
        return filtersRepository.getAllAreas().map { areas ->
            areas.filter { area ->
                area.parent != null && area.parent != "1001"
            }
        }
    }

    override suspend fun getRegionsByName(name: String): Flow<List<Area>> {
        return filtersRepository.getRegionsByName(name)
    }

    override suspend fun getRegionsByNameAndParent(name: String, parent: String?): Flow<List<Area>> {
        return filtersRepository.getRegionsByNameAndParent(name, parent)
    }

    override suspend fun getCountryById(id: Int): Flow<Area> {
        return filtersRepository.getCountryById(id)
    }

    override suspend fun downloadAreas(): Flow<Pair<AreasListDTO?, Int>> {
        return filtersRepository.downloadAreas().map {
            when (it) {
                is AreasListResponse -> Pair(it.areas, STATUS_OK)
                else -> Pair(null, it.resultCode)
            }
        }
    }

    override suspend fun downloadIndustries(): Flow<Pair<IndustriesListDAO?, Int>> {
        return filtersRepository.downloadIndustries().map {
            when (it) {
                is IndustriesListResponse -> Pair(it.industries, STATUS_OK)
                else -> Pair(null, it.resultCode)
            }
        }
    }

    companion object {
        const val STATUS_OK = 200
    }
}
