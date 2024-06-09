package ru.practicum.android.diploma.data.dto.vacancies.fields


import com.google.gson.annotations.SerializedName

data class Salary(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("from")
    val from: Int,
    @SerializedName("gross")
    val gross: Boolean,
    @SerializedName("to")
    val to: Any
)
