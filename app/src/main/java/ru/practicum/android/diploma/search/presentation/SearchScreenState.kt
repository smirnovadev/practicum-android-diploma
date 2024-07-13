package ru.practicum.android.diploma.search.presentation

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed class SearchScreenState {
    data object Default : SearchScreenState()
    data object Loading : SearchScreenState()
    data object InternetConnectionError : SearchScreenState()
    data object ServerError : SearchScreenState()
    data object SearchError : SearchScreenState()
    data object IOError : SearchScreenState()
    data object Error : SearchScreenState()
    data object UploadNextPage : SearchScreenState()
    data class ShowContent(val vacancies: ArrayList<Vacancy>, val found: Int) : SearchScreenState()
    data class UploadingError(val vacancies: ArrayList<Vacancy>, val found: Int) : SearchScreenState()
    data class UploadingInternetError(val vacancies: ArrayList<Vacancy>, val found: Int) : SearchScreenState()
}
