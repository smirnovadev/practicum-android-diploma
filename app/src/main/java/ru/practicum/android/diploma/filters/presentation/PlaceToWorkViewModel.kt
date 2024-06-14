package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.data.FiltersLocalStorage
import ru.practicum.android.diploma.search.domain.model.fields.Area

class PlaceToWorkViewModel(
    private val sharedInteractor: FiltersLocalStorage
) : ViewModel() {
    private val emptyArea = Area(-1, "")
    fun getCountryName(): String = (sharedInteractor.getCountry() ?: emptyArea).name
    fun getRegionName(): String = (sharedInteractor.getRegion() ?: emptyArea).name
}
