package ru.practicum.android.diploma.filters.domain.models

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

data class Filters(
    val salary: Int?,
    val salaryFlag: Boolean = false,
    val country: Area?,
    val region: Area?,
    val industry: Industry? = null
)
