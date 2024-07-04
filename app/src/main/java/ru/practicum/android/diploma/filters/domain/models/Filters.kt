package ru.practicum.android.diploma.filters.domain.models

data class Filters(
    val country: String,
    val region: String,
    val industry: String,
    val salary: String,
    val salaryFlag: Boolean
)
