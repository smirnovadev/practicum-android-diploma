package ru.practicum.android.diploma.data.dto.responses.fields


import com.google.gson.annotations.SerializedName

data class Contacts(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phones")
    val phones: List<ru.practicum.android.diploma.data.dto.responses.fields.Phone>
)
