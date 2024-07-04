package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filters.domain.FiltersInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractorSave
import ru.practicum.android.diploma.filters.domain.FiltersTransformInteractor
import ru.practicum.android.diploma.filters.domain.state.AreasState
import ru.practicum.android.diploma.search.domain.model.fields.Area

class RegionViewModel(
    private val interactor: FiltersInteractor,
    private val transformer: FiltersTransformInteractor,
    private val sharedInteractor: FiltersSharedInteractor,
    private val sharedInteractorSave: FiltersSharedInteractorSave
) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<AreasState>()
    private val regionsScreenState: LiveData<AreasState> = stateMutableLiveData
    fun getScreenStateLiveData() = regionsScreenState

    init {
        loadRegions()
    }

    fun saveAndExit(region: Area, navController: NavController) {
        viewModelScope.launch {
            sharedInteractorSave.saveRegion(region, isCurrent = true)
            val country = sharedInteractor.getCountry(isCurrent = true)
            if (country == null && region.parent != null) {
                val parentId = region.parent.toInt()
                interactor.getCountryById(parentId).collect { parentCountry ->
                    sharedInteractorSave.saveCountry(parentCountry, isCurrent = true)
                    navController.navigateUp()
                }
            } else {
                navController.navigateUp()
            }
        }
    }

    fun search(request: String) {
        val country = sharedInteractor.getCountry(isCurrent = true)
        if (country == null) {
            viewModelScope.launch {
                interactor
                    .getRegionsByName(request)
                    .collect { areas ->
                        if (areas.isEmpty()) {
                            renderState(AreasState.Empty)
                        } else {
                            val regions = areas.filter { region ->
                                region.parent != null && region.parent != "1001"
                            }
                            renderState(AreasState.Content(regions))
                        }
                    }
            }
        } else {
            viewModelScope.launch {
                interactor
                    .getRegionsByNameAndParent(request, country.id.toString())
                    .collect { areas ->
                        if (areas.isEmpty()) {
                            renderState(AreasState.Empty)
                        } else {
                            renderState(AreasState.Content(areas))
                        }
                    }
            }
        }
    }

    private fun renderState(state: AreasState) {
        stateMutableLiveData.postValue(state)
    }

    private fun loadRegions() {
        renderState(AreasState.Loading)
        val country = sharedInteractor.getCountry(isCurrent = true)
        if (country == null) {
            viewModelScope.launch {
                interactor
                    .getAllRegions()
                    .collect { regions ->
                        if (regions.isNotEmpty()) {
                            renderState(AreasState.Content(regions))
                        } else {
                            downloadAreasToBase()
                        }
                    }
            }
            return
        }
        viewModelScope.launch {
            interactor
                .getRegionsByParent(country.id.toString())
                .collect { regions ->
                    if (regions.isNotEmpty()) {
                        renderState(AreasState.Content(regions))
                    } else {
                        downloadAreasToBase()
                    }
                }
        }
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
                transformer.regionsFromDTO(result.first!!).also {
                    renderState(
                        if (it.isNotEmpty()) {
                            interactor.insertAreas(it)
                            val cisRegions = it.filter { area ->
                                area.parent != null && area.parent != "1001"
                            }
                            AreasState.Content(cisRegions)
                        } else {
                            AreasState.Empty
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val STATUS_OK = 200
    }
}
