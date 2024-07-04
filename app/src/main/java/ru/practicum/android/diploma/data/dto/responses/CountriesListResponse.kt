package ru.practicum.android.diploma.data.dto.responses

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasListDAO

data class CountriesListResponse(
    val countries: AreasListDAO
) : NetworkResponse()
