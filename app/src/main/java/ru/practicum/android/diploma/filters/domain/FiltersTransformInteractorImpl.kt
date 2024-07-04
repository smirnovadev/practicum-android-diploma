package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.data.dto.responses.areas.AreaUnitDTO
import ru.practicum.android.diploma.data.dto.responses.industry.IndustryUnitDTO
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersTransformInteractorImpl(
    private val repository: FiltersTransformRepository
) : FiltersTransformInteractor {
    override fun regionsFromDTO(input: List<AreaUnitDTO>): List<Area> = repository.regionsFromDTO(input)

    override fun countriesFromDTO(input: List<AreaUnitDTO>): List<Area> = repository.countriesFromDTO(input)

    override fun industriesFromDTO(input: List<IndustryUnitDTO>): List<Industry> = repository.industriesFromDTO(input)

    override fun filterIndustries(query: String, industries: List<Industry>): List<Industry> =
        repository.filterIndustries(query, industries)
}
