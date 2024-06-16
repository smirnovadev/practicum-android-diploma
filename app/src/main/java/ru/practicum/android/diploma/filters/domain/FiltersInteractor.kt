package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDAO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesListDAO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersInteractor {
    suspend fun insertIndustries(industries: List<Industry>)
    fun getIndustry(): Flow<List<Industry>>

    fun findIndustry(name: String): Flow<List<Industry>>

    suspend fun insertAreas(area: List<Area>)
    fun getCountries(): Flow<List<Area>>

    fun getRegions(parent: Int): Flow<List<Area>>

    fun getRegion(name: String, parent: Int): Flow<List<Area>>

    suspend fun downloadAreas(): Flow<Pair<AreasListDAO?, Int>>
    suspend fun downloadIndustries(): Flow<Pair<IndustriesListDAO?, Int>>
}
