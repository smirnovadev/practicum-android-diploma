package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.search.domain.SearchScreenState

class SearchViewModel : ViewModel() {

    private var previousRequest: String = ""

    private val screenState = MutableLiveData<SearchScreenState>(SearchScreenState.Default)
    fun getScreenState(): LiveData<SearchScreenState> = screenState

    init {
        screenState.postValue(SearchScreenState.Default)
    }

    fun clearSearchField() {
        previousRequest = ""
        screenState.postValue(SearchScreenState.Default)
    }

}
