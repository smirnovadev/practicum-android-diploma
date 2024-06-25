package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class SnippetDTO(
    @SerializedName("requirement")
    val requirement: String?,
    @SerializedName("responsibility")
    val responsibility: String?
)
