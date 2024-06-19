package ru.practicum.android.diploma.data.dto.requests

import ru.practicum.android.diploma.data.dto.NetworkRequest

data class CountriesRequest(
    val locale: String = "RU"
) : NetworkRequest
