package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class Gradient(
    @SerializedName("angle")
    val angle: Int,
    @SerializedName("color_list")
    val colorList: List<Color>
)
