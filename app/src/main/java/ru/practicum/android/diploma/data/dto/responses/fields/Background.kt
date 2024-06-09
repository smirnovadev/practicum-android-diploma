package ru.practicum.android.diploma.data.dto.responses.fields


import com.google.gson.annotations.SerializedName

data class Background(
    @SerializedName("color")
    val color: Any,
    @SerializedName("gradient")
    val gradient: ru.practicum.android.diploma.data.dto.responses.fields.Gradient
)
