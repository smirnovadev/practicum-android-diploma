package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.ui.area.AreasState
import ru.practicum.android.diploma.search.data.mapper.AreaMapper

class RegionViewModel(
    private val interactor: FiltersInteractor,
    private val mapper: AreaMapper
) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<AreasState>()
    private val regionsScreenState: LiveData<AreasState> = stateMutableLiveData
    fun getScreenStateLiveData() = regionsScreenState

    // TODO LOAD FROM SHARED
    val region = 113

    init {
        loadRegions()
    }

    private suspend fun downloadAreasToBase() {
        interactor.downloadAreas().collect { result ->
            if (result.first == null) {
                renderState(
                    if (result.second == 200)
                        AreasState.Empty
                    else
                        AreasState.Error(result.second)
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
                } else
                    renderState(AreasState.Empty)
            }
        }
    }

    private fun loadRegions() {
        renderState(AreasState.Loading)
        viewModelScope.launch {
            interactor
                .getRegions(region)
                .collect {
                    if (it.isNotEmpty())
                        renderState(AreasState.Content(it))
                    else {
                        downloadAreasToBase()
                    }
                }
        }
    }

    private fun renderState(state: AreasState) {
        stateMutableLiveData.postValue(state)
    }
}
