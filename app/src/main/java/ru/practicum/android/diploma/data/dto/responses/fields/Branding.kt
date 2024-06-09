package ru.practicum.android.diploma.data.dto.responses.fields


import com.google.gson.annotations.SerializedName

data class Branding(
    @SerializedName("tariff")
    val tariff: String,
    @SerializedName("type")
    val type: String
)
