package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class PictureScalable(
    @SerializedName("default")
    val default: Default,
    @SerializedName("xs")
    val xs: XsX
)
