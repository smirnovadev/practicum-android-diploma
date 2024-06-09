package ru.practicum.android.diploma.data.dto.responses.industry

data class IndustryUnit(
    val id: String,
    val name: String,
    val industries: List<ru.practicum.android.diploma.data.dto.responses.industry.IndustryUnit> = arrayListOf()
)
