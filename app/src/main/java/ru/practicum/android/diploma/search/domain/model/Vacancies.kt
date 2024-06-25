package ru.practicum.android.diploma.search.domain.model

data class Vacancies(
    val vacancies: List<Vacancy>,
    val found: Int,
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val fixes: String?,
    val suggests: String?
)
