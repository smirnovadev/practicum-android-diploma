package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Background(
    @SerializedName("color")
    val color: Any,
    @SerializedName("gradient")
    val gradient: Gradient
)
