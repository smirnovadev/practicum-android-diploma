package ru.practicum.android.diploma.search.ui.adapter

import ru.practicum.android.diploma.search.domain.model.Vacancy

fun interface SearchClickListener {
    fun onVacancyClick(vacancy: Vacancy)
}
