package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDTO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesListDAO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersInteractor {
    suspend fun insertIndustries(industries: List<Industry>)
    suspend fun getIndustry(): Flow<List<Industry>>

    suspend fun findIndustry(name: String): Flow<List<Industry>>

    suspend fun insertAreas(area: List<Area>)
    suspend fun getCountries(): Flow<List<Area>>

    suspend fun getRegionsByParent(parent: String?): Flow<List<Area>>

    suspend fun getRegionsByName(name: String): Flow<List<Area>>
    suspend fun getRegionsByNameAndParent(name: String, parent: String?): Flow<List<Area>>
    suspend fun getCountryById(id: Int): Flow<Area>
    suspend fun getAllRegions(): Flow<List<Area>>
    suspend fun downloadAreas(): Flow<Pair<AreasListDTO?, Int>>
    suspend fun downloadIndustries(): Flow<Pair<IndustriesListDAO?, Int>>
}
