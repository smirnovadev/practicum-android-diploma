package ru.practicum.android.diploma.search.presentation

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed class SearchScreenState {
    data object Default : SearchScreenState()
    data object Loading : SearchScreenState()
    data object InternetConnectionError : SearchScreenState()
    data object ServerError : SearchScreenState()
    data object SearchError : SearchScreenState()
    data object uploadNextPage : SearchScreenState()
    data class ShowContent(val vacancies: ArrayList<Vacancy>, val found: Int) : SearchScreenState()

}
