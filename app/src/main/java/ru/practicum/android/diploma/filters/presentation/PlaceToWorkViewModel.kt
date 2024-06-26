package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.search.domain.model.fields.Area

class PlaceToWorkViewModel(
    private val sharedInteractor: FiltersSharedInteractor
) : ViewModel() {
    private val emptyArea = Area(-1, "")

    fun getCountryName(): String = (sharedInteractor.getCountry() ?: emptyArea).name
    fun getRegionName(): String = (sharedInteractor.getRegion() ?: emptyArea).name

    fun clearCountryName() = sharedInteractor.saveCountry(null)
    fun clearRegionName() = sharedInteractor.saveRegion(null)
}
