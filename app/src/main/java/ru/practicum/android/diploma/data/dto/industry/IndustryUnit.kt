package ru.practicum.android.diploma.data.dto.industry

data class IndustryUnit(
    val id: String,
    val name: String,
    val industries: List<IndustryUnit> = arrayListOf()
)
