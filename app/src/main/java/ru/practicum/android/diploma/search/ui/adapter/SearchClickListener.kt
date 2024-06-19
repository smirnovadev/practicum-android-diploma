package ru.practicum.android.diploma.search.ui.adapter

import ru.practicum.android.diploma.data.dto.responses.VacancyByIdResponse

fun interface SearchClickListener {
    fun onVacancyClick(vacanacy: VacancyByIdResponse)
}
