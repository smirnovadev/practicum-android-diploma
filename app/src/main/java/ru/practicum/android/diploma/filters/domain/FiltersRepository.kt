package ru.practicum.android.diploma.filters.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersRepository {
    suspend fun insertIndustry(industry: Industry)
    suspend fun insertIndustries(industries: List<Industry>)
    suspend fun deleteIndustry(industry: Industry)
    fun getIndustry(): Flow<List<Industry>>

    suspend fun insertArea(area: Area)
    suspend fun insertAreas(area: List<Area>)
    suspend fun deleteArea(area: Area)
    fun getAllAreas(): Flow<List<Area>>
    fun getCountries(): Flow<List<Area>>
    fun getRegionsByParent(parent: String?): Flow<List<Area>>

    suspend fun downloadAreas(): Flow<NetworkResponse>

    suspend fun downloadIndustries(): Flow<NetworkResponse>

    fun getRegionsByName(name: String): Flow<List<Area>>
    suspend fun getCountryById(id: Int): Flow<Area>

    fun getRegionsByNameAndParent(name: String, parent: String?): Flow<List<Area>>
    fun findIndustry(name: String): Flow<List<Industry>>
}
