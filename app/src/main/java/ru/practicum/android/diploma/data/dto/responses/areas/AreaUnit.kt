package ru.practicum.android.diploma.data.dto.responses.areas

import com.google.gson.annotations.SerializedName

data class AreaUnit(
    val id: String,
    val name: String,
    val url: String,
    val parent: String? = null,
    @SerializedName("areas") val children: ArrayList<ru.practicum.android.diploma.data.dto.responses.areas.AreasList> = arrayListOf()
)
