package ru.practicum.android.diploma.data.dto.requests

import ru.practicum.android.diploma.data.dto.NetworkRequest

data class VacanciesSearchRequest(
    val query: String,
    val salary: String? = null,
    val page: Int = 0,
    val amount: Int = 20,
    val onlyWithSalary: Boolean = false,
    val industry: String? = null,
    val area: String? = null
) : NetworkRequest
