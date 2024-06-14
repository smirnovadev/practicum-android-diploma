package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.ui.industry.IndustryState

class IndustryViewModel(private val interactor: FiltersInteractor) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<IndustryState>()
    private val industryScreenState: LiveData<IndustryState> = stateMutableLiveData
    fun getScreenStateLiveData() = industryScreenState

    init {
        loadIndustry()
    }

    fun loadIndustry() {
        renderState(IndustryState.Loading)
        viewModelScope.launch {
            interactor
                .getIndustry()
                .collect {
                    if (it.isNotEmpty())
                        renderState(IndustryState.Content(it))
                    else
                        renderState(IndustryState.Empty)
                }
        }
    }

    private fun renderState(state: IndustryState) {
        stateMutableLiveData.postValue(state)
    }

}
