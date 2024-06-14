package ru.practicum.android.diploma.data.dto.responses.areas

import com.google.gson.annotations.SerializedName

data class AreaUnitDAO(
    val id: String,
    val name: String,
    val url: String,
    val parent: String? = null,
    @SerializedName("areas") val children: ArrayList<AreaUnitDAO>? = arrayListOf()
)
