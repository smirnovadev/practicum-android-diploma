package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("color")
    val color: String,
    @SerializedName("position")
    val position: Int
)
