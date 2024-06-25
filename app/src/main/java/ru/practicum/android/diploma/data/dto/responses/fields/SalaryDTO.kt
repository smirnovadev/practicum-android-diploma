package ru.practicum.android.diploma.data.dto.responses.fields

import com.google.gson.annotations.SerializedName

data class SalaryDTO(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("gross")
    val gross: Boolean?,
    @SerializedName("to")
    val to: Int?
)
