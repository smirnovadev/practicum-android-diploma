package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.data.dto.responses.areas.AreaUnitDTO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustryUnitDTO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersTransformInteractor {
    fun regionsFromDTO(input: List<AreaUnitDTO>): List<Area>
    fun countriesFromDTO(input: List<AreaUnitDTO>): List<Area>
    fun industriesFromDTO(input: List<IndustryUnitDTO>): List<Industry>
    fun filterIndustries(query: String, industries: List<Industry>): List<Industry>
}
