package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.FiltersTransformInteractor
import ru.practicum.android.diploma.filters.domain.state.IndustryState
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class IndustryViewModel(
    private val interactor: FiltersInteractor,
    private val transformer: FiltersTransformInteractor,
    private val sharedInteractor: FiltersSharedInteractor,
) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<IndustryState>()
    private val industryScreenState: LiveData<IndustryState> = stateMutableLiveData
    fun getScreenStateLiveData() = industryScreenState

    private val industries = mutableListOf<Industry>()

    init {
        loadIndustry()
    }

    private fun loadIndustry() {
        renderState(IndustryState.Loading)
        viewModelScope.launch {
            interactor.downloadIndustries().collect { result ->
                if (result.first == null) {
                    renderState(
                        if (result.second == STATUS_OK) {
                            IndustryState.Empty
                        } else {
                            IndustryState.Error
                        }
                    )
                } else {
                    transformer.industriesFromDTO(result.first!!).also {
                        if (it.isNotEmpty()) {
                            industries.clear()
                            industries.addAll(it)
                            renderState(IndustryState.Content(it))
                        } else {
                            renderState(IndustryState.Empty)
                        }
                    }
                }
            }
        }
    }

    fun save(industry: Industry) {
        sharedInteractor.saveCurrentIndustry(industry)
    }

    private fun renderState(state: IndustryState) {
        stateMutableLiveData.postValue(state)
    }

    fun search(request: String) {
        viewModelScope.launch {
            val filterIndustries = transformer.filterIndustries(request, industries)
            if (filterIndustries.isEmpty()) {
                renderState(IndustryState.Empty)
            } else {
                renderState(IndustryState.Content(filterIndustries))
            }
        }
    }

    fun getIndustry(): Industry? = sharedInteractor.getCurrentIndustry()

    fun clearIndustry() = sharedInteractor.saveCurrentIndustry(null)

    companion object {
        const val STATUS_OK = 200
    }
}
