package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersViewModel(private val sharedInteractor: FiltersSharedInteractor) : ViewModel() {
    fun getCountryName(): String = (sharedInteractor.getCountry() ?: EMPTY_AREA).name
    fun getRegionName(): String = (sharedInteractor.getRegion() ?: EMPTY_AREA).name
    fun getIndustryName(): String = (sharedInteractor.getIndustry() ?: EMPTY_INDUSTRY).name
    fun getSalary(): String = sharedInteractor.getSalary()?.toString() ?: ""
    fun getSalaryFlag(): Boolean = sharedInteractor.getSalaryFlag() ?: DEFAULT_SALARY_FLAG

    fun saveSalary(salary: String?) = sharedInteractor.saveSalary(salary?.toIntOrNull())
    fun saveSalaryFlag(flag: Boolean) = sharedInteractor.saveSalaryFlag(flag)

    fun clearCountryName() = sharedInteractor.deleteCountry()
    fun clearRegionName() = sharedInteractor.deleteRegion()
    fun clearIndustryName() = sharedInteractor.deleteIndustry()
    fun clearSalary() = sharedInteractor.deleteSalary()
    fun clearSalaryFlag() = sharedInteractor.deleteSalaryFlag()

    companion object {
        const val DEFAULT_SALARY_FLAG = false
        val EMPTY_AREA = Area(-1, "")
        val EMPTY_INDUSTRY = Industry(-1, "")
    }
}
