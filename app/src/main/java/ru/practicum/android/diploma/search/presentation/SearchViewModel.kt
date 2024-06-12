package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.search.domain.model.VacancySnippet

class SearchViewModel(
) : ViewModel() {

    private var previousRequest: String = ""

    private val vacanciesLiveData = MutableLiveData<List<VacancySnippet>>()
    private val screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Default)
    fun getScreenState(): LiveData<SearchScreenState> = screenState
    fun getVacanciesLiveData(): LiveData<List<VacancySnippet>> = vacanciesLiveData

    init {
        screenState.postValue(SearchScreenState.Default)
    }

    fun clearSearchField() {
        previousRequest = ""
        screenState.postValue(SearchScreenState.Default)
    }

}
