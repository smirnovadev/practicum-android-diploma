package ru.practicum.android.diploma.data.dto.responses.areas

import com.google.gson.annotations.SerializedName

data class AreaUnitDTO(
    val id: String,
    val name: String,
    val parent: String? = null,
    @SerializedName("areas") val children: ArrayList<AreaUnitDTO>? = arrayListOf()
)
