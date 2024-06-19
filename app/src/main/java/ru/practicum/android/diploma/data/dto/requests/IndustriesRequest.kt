package ru.practicum.android.diploma.data.dto.requests

import ru.practicum.android.diploma.data.dto.NetworkRequest

data class IndustriesRequest(
    val locale: String = "RU"
) : NetworkRequest
