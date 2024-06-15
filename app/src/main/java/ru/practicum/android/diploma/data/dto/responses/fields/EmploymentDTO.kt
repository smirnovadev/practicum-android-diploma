package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class EmploymentDTO(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
)
