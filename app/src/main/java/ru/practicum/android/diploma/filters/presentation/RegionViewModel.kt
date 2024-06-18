package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.ui.area.AreasState
import ru.practicum.android.diploma.search.data.mapper.AreaMapper
import ru.practicum.android.diploma.search.domain.model.fields.Area

class RegionViewModel(
    private val interactor: FiltersInteractor,
    private val mapper: AreaMapper,
    private val sharedInteractor: FiltersSharedInteractor
) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<AreasState>()
    private val regionsScreenState: LiveData<AreasState> = stateMutableLiveData
    fun getScreenStateLiveData() = regionsScreenState

    init {
        loadRegions()
    }

    private suspend fun downloadAreasToBase() {
        interactor.downloadAreas().collect { result ->
            if (result.first == null) {
                renderState(
                    if (result.second == STATUS_OK) {
                        AreasState.Empty
                    } else {
                        AreasState.Error(result.second)
                    }
                )
            } else {
                val areas = mapper.map(result.first!!, 1)
                if (areas.isNotEmpty()) {
                    interactor.insertAreas(areas)
                    renderState(AreasState.Content(
                        areas.filter { area ->
                            area.parent != null
                        }
                    ))
                } else {
                    renderState(AreasState.Empty)
                }
            }
        }
    }

    private fun loadRegions() {
        val country = sharedInteractor.getCountry()
        if (country == null) {
            renderState(AreasState.Empty)
            return
        }
        renderState(AreasState.Loading)
        viewModelScope.launch {
            interactor
                .getRegions(country.id)
                .collect {
                    if (it.isNotEmpty()) {
                        renderState(AreasState.Content(it))
                    } else {
                        downloadAreasToBase()
                    }
                }
        }
    }

    fun save(region: Area) {
        sharedInteractor.saveRegion(region)
    }

    private fun renderState(state: AreasState) {
        stateMutableLiveData.postValue(state)
    }

    fun search(request: String) {
        val country = sharedInteractor.getCountry()
        if (country == null) {
            renderState(AreasState.Empty)
            return
        }

        viewModelScope.launch {
            interactor
                .getRegion(request, country.id)
                .collect {
                    renderState(AreasState.Content(it))
                }
        }
    }

    companion object {
        const val STATUS_OK = 200
    }
}
