package ru.practicum.android.diploma.data.dto.responses

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.industry.IndustriesList

data class IndustriesListResponse(
    val industries: IndustriesList
) : NetworkResponse()
