package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.data.FiltersLocalStorage
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry
import ru.practicum.android.diploma.search.domain.model.fields.Salary

class FiltersViewModel(private val sharedInteractor: FiltersLocalStorage) : ViewModel() {
    private val emptyArea = Area(-1, "")
    private val emptyIndustry = Industry(-1, "")
//    private val emptySalary = Salary(-1, "")

    fun getCountryName(): String = (sharedInteractor.getCountry() ?: emptyArea).name
    fun getRegionName(): String = (sharedInteractor.getRegion() ?: emptyArea).name
    fun getIndustryName(): String = (sharedInteractor.getIndustry() ?: emptyIndustry).name

    fun clearCountryName() = (sharedInteractor.deleteCountry())
    fun clearRegionName() = (sharedInteractor.deleteRegion())
    fun clearIndustryName() = (sharedInteractor.deleteIndustry())
}
