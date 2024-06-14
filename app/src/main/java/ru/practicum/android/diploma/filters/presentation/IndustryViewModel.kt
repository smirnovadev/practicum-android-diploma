package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.ui.industry.IndustryState
import ru.practicum.android.diploma.search.data.mapper.IndustryMapper

class IndustryViewModel(
    private val interactor: FiltersInteractor,
    private val mapper: IndustryMapper
) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<IndustryState>()
    private val industryScreenState: LiveData<IndustryState> = stateMutableLiveData
    fun getScreenStateLiveData() = industryScreenState

    init {
        loadIndustry()
    }

    private suspend fun downloadIndustriesToBase() {
        interactor.downloadIndustries().collect {
            if (it.first == null) renderState(IndustryState.Error(it.second))
            else {
                val industries = mapper.map(it.first!!)
                if (industries.isNotEmpty()) {
                    interactor.insertIndustries(industries)
                    renderState(IndustryState.Content(industries))
                } else
                    renderState(IndustryState.Empty)
            }
        }
    }

    fun loadIndustry() {
        renderState(IndustryState.Loading)
        viewModelScope.launch {
            interactor
                .getIndustry()
                .collect {
                    if (it.isNotEmpty())
                        renderState(IndustryState.Content(it))
                    else {
                        downloadIndustriesToBase()
                    }
                }
        }
    }

    private fun renderState(state: IndustryState) {
        stateMutableLiveData.postValue(state)
    }

}
