package ru.practicum.android.diploma.data.dto.responses.industry

data class IndustryUnitDTO(
    val id: String,
    val name: String,
    val industries: List<IndustryUnitDTO>? = arrayListOf(),
    val parent: String? = null
)
