package ru.practicum.android.diploma.data.dto.responses

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.areas.AreasList

data class CountriesListResponse(
    val countries: AreasList
) : NetworkResponse()
