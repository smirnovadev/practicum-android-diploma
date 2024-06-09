package ru.practicum.android.diploma.data.dto.vacancy


import com.google.gson.annotations.SerializedName

data class LogoScalable(
    @SerializedName("default")
    val default: Default,
    @SerializedName("xs")
    val xs: Xs
)
