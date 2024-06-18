package ru.practicum.android.diploma.search.domain.model.fields

import java.io.Serializable

data class Address(
    val building: String,
    val city: String,
    val description: String,
    val lat: Double,
    val lng: Double,
    val street: String
) : Serializable
