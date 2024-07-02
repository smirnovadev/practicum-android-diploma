package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.FiltersTransformInteractor
import ru.practicum.android.diploma.filters.domain.state.AreasState
import ru.practicum.android.diploma.search.domain.model.fields.Area

class CountryViewModel(
    private val interactor: FiltersInteractor,
    private val transformer: FiltersTransformInteractor,
    private val sharedInteractor: FiltersSharedInteractor
) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<AreasState>()
    private val countriesScreenState: LiveData<AreasState> = stateMutableLiveData
    fun getScreenStateLiveData() = countriesScreenState

    init {
        loadCountries()
    }

    private suspend fun downloadAreasToBase() {
        interactor.downloadAreas().collect { result ->
            if (result.first == null) {
                renderState(
                    if (result.second == STATUS_OK) {
                        AreasState.Empty
                    } else {
                        AreasState.Error
                    }
                )
            } else {
                transformer.countriesFromDTO(result.first!!).also { areas ->
                    renderState(
                        if (areas.isNotEmpty()) {
                            interactor.insertAreas(areas)
                            val countries = areas
                                .filter { area -> area.parent == null }
                                .sortedBy { it.id }
                            AreasState.Content(countries)
                        } else {
                            AreasState.Empty
                        }
                    )
                }
            }
        }
    }

    private fun loadCountries() {
        renderState(AreasState.Loading)
        viewModelScope.launch {
            interactor
                .getCountries()
                .collect {
                    if (it.isNotEmpty()) {
                        renderState(AreasState.Content(it))
                    } else {
                        downloadAreasToBase()
                    }
                }
        }
    }

    fun save(country: Area) {
        sharedInteractor.saveCurrentCountry(country)
    }

    private fun renderState(state: AreasState) {
        stateMutableLiveData.postValue(state)
    }

    companion object {
        const val STATUS_OK = 200
    }
}
