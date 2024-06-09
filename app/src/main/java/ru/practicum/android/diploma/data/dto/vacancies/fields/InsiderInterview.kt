package ru.practicum.android.diploma.data.dto.vacancies.fields


import com.google.gson.annotations.SerializedName

data class InsiderInterview(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)
