package ru.practicum.android.diploma.data.dto.vacancies.fields


import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("requirement")
    val requirement: String,
    @SerializedName("responsibility")
    val responsibility: String
)
