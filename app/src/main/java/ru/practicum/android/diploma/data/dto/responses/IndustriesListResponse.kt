package ru.practicum.android.diploma.data.dto.responses

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.responses.industry.IndustriesListDAO

data class IndustriesListResponse(
    val industries: IndustriesListDAO
) : NetworkResponse()
