package ru.practicum.android.diploma.data.dto.responses

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDTO

data class CountriesListResponse(
    val countries: AreasListDTO
) : NetworkResponse()
