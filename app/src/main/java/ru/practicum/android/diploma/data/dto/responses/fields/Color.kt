package ru.practicum.android.diploma.data.dto.responses.fields


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("color")
    val color: String,
    @SerializedName("position")
    val position: Int
)
