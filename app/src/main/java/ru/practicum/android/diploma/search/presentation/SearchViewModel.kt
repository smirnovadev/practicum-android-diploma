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
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.models.FiltersParameters
import ru.practicum.android.diploma.filters.domain.models.FiltersState
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancies
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val filtersSharedInteractor: FiltersSharedInteractor
) : ViewModel() {

    private var previousRequest: String = ""
    private var unprocessedRequest: String = ""
    private var searchResultsList = ArrayList<Vacancy>()
    private var currentPage: Int = 0
    private var maxPages: Int = 0
    private var found: Int = 0
    private var currentFilters = getFilters()
    private var isNextPageLoading: Boolean = false
    private var isUploading: Boolean = false

    val searchDebounce = debounce<String>(
        SEARCH_DEBOUNCE_DELAY,
        viewModelScope,
        true
    ) { request ->
        if (request != previousRequest && !isNextPageLoading) {
            search(request)
        }
    }

    private val screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Default)
    fun getScreenState(): LiveData<SearchScreenState> = screenState

    private val filtersState = MutableLiveData<FiltersState>(FiltersState.Inactive)
    fun getFiltersState(): LiveData<FiltersState> = filtersState

    init {
        screenState.postValue(SearchScreenState.Default)
        processFiltersStatus(getFilters())
    }

    fun search(request: String, page: Int = 0) {
        if (request != previousRequest) {
            searchResultsList.clear()
            currentPage = 0
            maxPages = 0
            found = 0
            isUploading = false
            screenState.postValue(SearchScreenState.Loading)
        }

        isNextPageLoading = true
        if (request.isNotEmpty()) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    searchInteractor
                        .getVacancies(request, page, currentFilters)
                        .catch { exception ->
                            if (!isUploading) {
                                previousRequest = request
                                screenState.postValue(SearchScreenState.Error)
                                isNextPageLoading = false
                            } else {
                                SearchScreenState.UploadingError(
                                    searchResultsList,
                                    found
                                )
                                isNextPageLoading = false
                            }
                        }
                        .collect { pair -> processResults(pair.data, pair.message, request) }
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
                if (currentPage == ZERO) found = vacancies.found
                Log.d(TAG_SEARCH, "Max pages: $maxPages")
                screenState.postValue(SearchScreenState.ShowContent(searchResultsList, found))
                unprocessedRequest = ""
                isNextPageLoading = false
            } else {
                screenState.postValue(SearchScreenState.SearchError)
                isNextPageLoading = false
            }
        } else {
            when (errorCode) {
                ERROR_NO_INTERNET -> {
                    if (!isUploading) {
                        previousRequest = ""
                        unprocessedRequest = searchRequest
                        screenState.postValue(SearchScreenState.InternetConnectionError)
                    } else {
                        screenState.postValue(SearchScreenState.UploadingInternetError(searchResultsList, found))
                    }
                    isNextPageLoading = false
                }

                IO_EXCEPTION -> {
                    previousRequest = searchRequest
                    if (!isUploading) {
                        screenState.postValue(SearchScreenState.IOError)
                    } else {
                        screenState.postValue(SearchScreenState.UploadingError(searchResultsList, found))
                    }
                    isNextPageLoading = false
                }

                else -> {
                    previousRequest = searchRequest
                    if (!isUploading) {
                        screenState.postValue(SearchScreenState.ServerError)
                    } else {
                        screenState.postValue(SearchScreenState.UploadingError(searchResultsList, found))
                    }
                    isNextPageLoading = false
                }
            }
        }
    }

    fun uploadPage() {
        if (previousRequest.isNotEmpty() && !isNextPageLoading && currentPage != maxPages - ONE) {
            isNextPageLoading = true
            isUploading = true
            screenState.postValue(SearchScreenState.UploadNextPage)
            search(previousRequest, currentPage + ONE)
        }
    }

    fun prepareOnResumeState() {
        if (screenState.value is SearchScreenState.UploadingError ||
            screenState.value is SearchScreenState.UploadingInternetError
        ) {
            screenState.postValue(SearchScreenState.ShowContent(searchResultsList, found))
        } else if (
            screenState.value is SearchScreenState.IOError ||
            screenState.value is SearchScreenState.Error
        ) {
            screenState.postValue(SearchScreenState.Default)
        }
    }

    fun clearSearchField() {
        previousRequest = ""
        unprocessedRequest = ""
        isUploading = false
        isNextPageLoading = false
        searchDebounce("")
        searchResultsList.clear()
        screenState.postValue(SearchScreenState.Default)
    }

    private fun getFilters(): FiltersParameters {
        return FiltersParameters(
            salary = filtersSharedInteractor.getSalary(),
            salaryFlag = filtersSharedInteractor.getSalaryFlag() ?: false,
            industry = processIndustry(filtersSharedInteractor.getIndustry()),
            area = processArea(
                filtersSharedInteractor.getCountry(),
                filtersSharedInteractor.getRegion()
            )
        )
    }

    private fun processFiltersStatus(filters: FiltersParameters) {
        var isActive = filters.salary != null ||
            filters.industry != null ||
            filters.area != null
        if (filters.salaryFlag) isActive = true
        if (isActive) filtersState.postValue(FiltersState.Active) else filtersState.postValue(FiltersState.Inactive)
    }

    fun checkFiltersStatus() {
        val filters = getFilters()
        if (filters != currentFilters) {
            currentFilters = filters
            processFiltersStatus(currentFilters)
            if (previousRequest.isNotEmpty()) repeatRequest()
        }
    }

    private fun repeatRequest() {
        searchResultsList.clear()
        search(previousRequest, ZERO)
    }

    private fun processArea(country: Area?, region: Area?): String? {
        val result: String? = if (country == null && region == null) {
            null
        } else if (country != null && region == null) {
            country.id.toString()
        } else {
            region?.id.toString()
        }
        return result
    }

    private fun processIndustry(industry: Industry?): String? {
        return industry?.id?.toString()
    }

    fun actionDoneRequest(request: String) {
        if (unprocessedRequest.isNotEmpty()) {
            search(request, currentPage)
        } else if (request != previousRequest) {
            search(request)
        }
    }

    companion object {
        private const val ERROR_NO_INTERNET = -1
        private const val IO_EXCEPTION = -2
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val ONE = 1
        private const val ZERO = 0
        private const val TAG_SEARCH = "SEARCH RESPONSE"
    }
}
