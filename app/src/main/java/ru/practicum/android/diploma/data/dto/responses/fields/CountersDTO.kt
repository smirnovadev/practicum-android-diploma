package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class CountersDTO(
    @SerializedName("responses")
    val responses: Int?
)
