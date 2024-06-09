package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class LogoUrls(
    @SerializedName("90")
    val x90: String,
    @SerializedName("240")
    val x240: String,
    @SerializedName("original")
    val original: String
)
