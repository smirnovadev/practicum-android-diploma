package ru.practicum.android.diploma.filters.domain.models

data class FiltersParameters(
    val salary: Int?,
    val salaryFlag: Boolean = false,
    val industry: String? = null,
    val area: String?
)
