package ru.practicum.android.diploma.search.presentation

sealed class SearchScreenState {
    data object Default : SearchScreenState()
    data object Loading : SearchScreenState()
    data object InternetConnectionError : SearchScreenState()
    data object ServerError : SearchScreenState()
    data object SearchError : SearchScreenState()

}
