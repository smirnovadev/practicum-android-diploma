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

    suspend fun insertAreas(area: Area)
    suspend fun deleteArea(area: Area)
    fun getArea(): Flow<List<Area>>

    suspend fun downloadAreas(): Flow<NetworkResponse>

    suspend fun downloadIndustries(): Flow<NetworkResponse>
}
