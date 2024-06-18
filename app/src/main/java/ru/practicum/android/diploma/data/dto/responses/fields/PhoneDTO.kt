package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class PhoneDTO(
    @SerializedName("city")
    val city: String?,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("number")
    val number: String?
)
