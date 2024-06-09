package ru.practicum.android.diploma.data.dto.responses.fields


import com.google.gson.annotations.SerializedName

data class Gradient(
    @SerializedName("angle")
    val angle: Int,
    @SerializedName("color_list")
    val colorList: List<ru.practicum.android.diploma.data.dto.responses.fields.Color>
)
