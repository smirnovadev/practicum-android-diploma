package ru.practicum.android.diploma.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import debounce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancies
import ru.practicum.android.diploma.search.domain.model.Vacancy

class SearchViewModel(private val searchInteractor: SearchInteractor) : ViewModel() {

    private var previousRequest: String = ""
    private var searchResultsList = ArrayList<Vacancy>()
    private var currentPage: Int = 0
    private var maxPages: Int = 0
    private var isNextPageLoading: Boolean = false
    val searchDebounce = debounce<String>(
        SEARCH_DEBOUNCE_DELAY,
        viewModelScope,
        true
    ) { request -> search(request) }

    private val screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Default)
    fun getScreenState(): LiveData<SearchScreenState> = screenState

    init {
        screenState.postValue(SearchScreenState.Default)
    }

    fun search(request: String, page: Int = 0) {
        if (request != previousRequest) {
            searchResultsList.clear()
            currentPage = 0
            maxPages = 0
            screenState.postValue(SearchScreenState.Loading)
        }

        isNextPageLoading = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                searchInteractor
                    .getVacancies(request, page)
                    .catch { exception ->
                        screenState.postValue(SearchScreenState.Error)
                        isNextPageLoading = false
                    }
                    .collect { pair ->
                        processResults(pair.data, pair.message, request)
                    }
            }
        }
    }

    private fun processResults(vacancies: Vacancies?, errorCode: Int?, searchRequest: String) {
        if (vacancies != null) {
            previousRequest = searchRequest
            if (vacancies.vacancies.isNotEmpty()) {
                searchResultsList.addAll(vacancies.vacancies)
                currentPage = vacancies.page
                maxPages = vacancies.pages
                Log.d(TAG_SEARCH, "Max pages: $maxPages")
                screenState.postValue(SearchScreenState.ShowContent(searchResultsList, vacancies.found))
                isNextPageLoading = false
            } else {
                screenState.postValue(SearchScreenState.SearchError)
                isNextPageLoading = false
            }
        } else {
            when (errorCode) {
                ERROR_NO_INTERNET -> {
                    screenState.postValue(SearchScreenState.InternetConnectionError)
                    isNextPageLoading = false
                }

                IO_EXCEPTION -> {
                    screenState.postValue(SearchScreenState.Error)
                    isNextPageLoading = false
                }

                else -> {
                    screenState.postValue(SearchScreenState.ServerError)
                    isNextPageLoading = false
                }
            }
        }
    }

    fun uploadPage() {
        if (!isNextPageLoading && currentPage != maxPages - ONE) {
            isNextPageLoading = true
            screenState.postValue(SearchScreenState.UploadNextPage)
            search(previousRequest, currentPage + ONE)
        }
    }

    fun clearSearchField() {
        previousRequest = ""
        searchResultsList.clear()
        screenState.postValue(SearchScreenState.Default)
    }

    companion object {
        private const val ERROR_NO_INTERNET = -1
        private const val IO_EXCEPTION = -2
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val ONE = 1
        private const val TAG_SEARCH = "SEARCH RESPONSE"
    }
}
