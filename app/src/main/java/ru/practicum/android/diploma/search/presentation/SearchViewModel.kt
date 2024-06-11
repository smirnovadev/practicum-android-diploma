package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.VacancySnippet

class SearchViewModel(
    searchInteractor: SearchInteractor
) : ViewModel() {

    private var previousRequest: String = ""

    private val vacanciesLiveData = MutableLiveData<List<VacancySnippet>>()
    private val screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Default)
    fun getScreenState(): LiveData<SearchScreenState> = screenState
    fun getVacanciesLiveData(): LiveData<List<VacancySnippet>> = vacanciesLiveData

    init {
        viewModelScope.launch {
            searchInteractor.getVacancies("Андроид-разработчик").collect {
                vacanciesLiveData.postValue(it)
            }
        }
        screenState.postValue(SearchScreenState.Default)
    }

    fun clearSearchField() {
        previousRequest = ""
        screenState.postValue(SearchScreenState.Default)
    }

}
