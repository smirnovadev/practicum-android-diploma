package ru.practicum.android.diploma.search.domain.model.fields

data class Address(
    val building: String,
    val city: String,
    val description: String,
    val lat: Double,
    val lng: Double,
    val metroStations: List<MetroStation>,
    val street: String
)
