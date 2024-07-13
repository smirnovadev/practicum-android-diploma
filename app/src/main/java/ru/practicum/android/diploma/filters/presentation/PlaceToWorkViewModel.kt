package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractorSave
import ru.practicum.android.diploma.search.domain.model.fields.Area

class PlaceToWorkViewModel(
    private val sharedInteractor: FiltersSharedInteractor,
    private val sharedInteractorSave: FiltersSharedInteractorSave
) : ViewModel() {
    private val emptyArea = Area(-1, "")

    fun getCountryName(): String = (sharedInteractor.getCountry(isCurrent = true) ?: emptyArea).name
    fun getRegionName(): String = (sharedInteractor.getRegion(isCurrent = true) ?: emptyArea).name

    fun clearCountryName() {
        sharedInteractorSave.saveCountry(null, isCurrent = true)
    }

    fun clearRegionName() {
        sharedInteractorSave.saveRegion(null, isCurrent = true)
    }
}
