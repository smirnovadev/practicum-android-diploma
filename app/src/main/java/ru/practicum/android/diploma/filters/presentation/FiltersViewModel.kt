package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersViewModel(private val sharedInteractor: FiltersSharedInteractor) : ViewModel() {
    private val emptyArea = Area(-1, "")
    private val emptyIndustry = Industry(-1, "")

    fun getCountryName(): String = (sharedInteractor.getCountry() ?: emptyArea).name
    fun getRegionName(): String = (sharedInteractor.getRegion() ?: emptyArea).name
    fun getIndustryName(): String = (sharedInteractor.getIndustry() ?: emptyIndustry).name
    fun getSalary(): String = sharedInteractor.getSalary()?.toString() ?: ""

    fun saveSalary(salary: String?) = sharedInteractor.saveSalary(salary?.toIntOrNull())

    fun clearCountryName() = sharedInteractor.deleteCountry()
    fun clearRegionName() = sharedInteractor.deleteRegion()
    fun clearIndustryName() = sharedInteractor.deleteIndustry()
    fun clearSalary() = sharedInteractor.deleteSalary()
}
