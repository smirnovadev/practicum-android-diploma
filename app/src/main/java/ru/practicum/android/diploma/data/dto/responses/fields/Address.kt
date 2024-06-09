package ru.practicum.android.diploma.data.dto.responses.fields


import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.responses.fields.MetroStation

data class Address(
    @SerializedName("building")
    val building: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("metro_stations")
    val metroStations: List<ru.practicum.android.diploma.data.dto.responses.fields.MetroStation>,
    @SerializedName("street")
    val street: String
)
