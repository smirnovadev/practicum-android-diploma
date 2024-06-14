package ru.practicum.android.diploma.data.dto.responses.industry

data class IndustryUnitDAO(
    val id: String,
    val name: String,
    val industries: List<IndustryUnitDAO> = arrayListOf(),
    val parent: String? = null
)
