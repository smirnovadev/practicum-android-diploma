package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.ui.industry.IndustryState
import ru.practicum.android.diploma.search.data.mapper.IndustryMapper
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryViewModel(
    private val interactor: FiltersInteractor,
    private val mapper: IndustryMapper,
    private val sharedInteractor: FiltersSharedInteractor,
) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<IndustryState>()
    private val industryScreenState: LiveData<IndustryState> = stateMutableLiveData
    fun getScreenStateLiveData() = industryScreenState

    init {
        loadIndustry()
    }

    private suspend fun downloadIndustriesToBase() {
        interactor.downloadIndustries().collect { result ->
            if (result.first == null) {
                renderState(
                    if (result.second == STATUS_OK) {
                        IndustryState.Empty
                    } else {
                        IndustryState.Error(result.second)
                    }
                )
            } else {
                val industries = mapper.map(result.first!!)
                if (industries.isNotEmpty()) {
                    interactor.insertIndustries(industries)
                    renderState(IndustryState.Content(industries))
                } else {
                    renderState(IndustryState.Empty)
                }
            }
        }
    }

    private fun loadIndustry() {
        renderState(IndustryState.Loading)
        viewModelScope.launch {
            interactor
                .getIndustry()
                .collect {
                    if (it.isNotEmpty()) {
                        renderState(IndustryState.Content(it))
                    } else {
                        downloadIndustriesToBase()
                    }
                }
        }
    }

    fun save(industry: Industry) {
        sharedInteractor.saveIndustry(industry)
    }

    private fun renderState(state: IndustryState) {
        stateMutableLiveData.postValue(state)
    }

    fun search(request: String) {
        viewModelScope.launch {
            interactor
                .findIndustry(request)
                .collect {
                    renderState(IndustryState.Content(it))
                }
        }
    }

    fun getIndustry(): Industry? = sharedInteractor.getIndustry()

    fun clearIndustry() = sharedInteractor.saveIndustry(null)

    companion object {
        const val STATUS_OK = 200
    }
}
