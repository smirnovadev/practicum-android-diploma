package ru.practicum.android.diploma.data.dto.vacancies.fields


import com.google.gson.annotations.SerializedName

data class ClusterGroup(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
