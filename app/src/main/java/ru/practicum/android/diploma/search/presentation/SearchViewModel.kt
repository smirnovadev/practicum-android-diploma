package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancies
import ru.practicum.android.diploma.search.domain.model.Vacancy

class SearchViewModel(private val searchInteractor: SearchInteractor) : ViewModel() {

    private var previousRequest: String = ""
    private var unprocessedRequest: String = ""
    private var searchResultsList = ArrayList<Vacancy>()
    private val screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Default)
    fun getScreenState(): LiveData<SearchScreenState> = screenState

    init {
        screenState.postValue(SearchScreenState.Default)
    }

    fun search(request: String) {
        if (request != previousRequest) previousRequest = request
        if (request.isNotBlank()) {
            searchResultsList.clear()
            unprocessedRequest = ""
            screenState.postValue(SearchScreenState.Loading)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    searchInteractor
                        .getVacancies(request)
                        .collect { pair ->
                            processResults(pair.data, pair.message, request)
                        }
                }
            }
        }
    }

    private fun processResults(vacancies: Vacancies?, errorCode: Int?, searchRequest: String) {
        if (vacancies != null) {
            if (vacancies.vacancies.isNotEmpty()) {
                searchResultsList.clear()
                searchResultsList.addAll(vacancies.vacancies)
                screenState.postValue(SearchScreenState.ShowContent(searchResultsList, vacancies.found))
            } else {
                screenState.postValue(SearchScreenState.SearchError)
            }
        } else {
            when (errorCode) {
                ERROR_NO_INTERNET -> {
                    screenState.postValue(SearchScreenState.InternetConnectionError)
                    unprocessedRequest = searchRequest
                }

                IO_EXCEPTION -> {
                    //уточнить в ТЗ
                    screenState.postValue(SearchScreenState.ServerError)
                    unprocessedRequest = searchRequest
                }

                else -> screenState.postValue(SearchScreenState.ServerError)

            }
        }
    }

    fun clearSearchField() {
        previousRequest = ""
        screenState.postValue(SearchScreenState.Default)
    }

    companion object {
        private const val ERROR_NO_INTERNET = -1
        private const val IO_EXCEPTION = -2
    }

}
