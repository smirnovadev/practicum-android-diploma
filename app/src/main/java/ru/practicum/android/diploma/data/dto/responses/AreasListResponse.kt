package ru.practicum.android.diploma.data.dto.responses

import ru.practicum.android.diploma.data.dto.NetworkResponse
import ru.practicum.android.diploma.data.dto.areas.AreasList

data class AreasListResponse(
    val areas: AreasList
):NetworkResponse()
