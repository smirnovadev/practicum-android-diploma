package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class InsiderInterviewDTO(
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?
)
