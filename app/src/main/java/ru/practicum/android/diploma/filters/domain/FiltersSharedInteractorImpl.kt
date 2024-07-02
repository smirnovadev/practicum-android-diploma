package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersSharedInteractorImpl(
    private val filtersLocalStorage: FiltersLocalStorage,
    private val filtersLocalStorageSave: FiltersLocalStorageSave
) : FiltersSharedInteractor {
    override fun applyFilter() {
        filtersLocalStorageSave.saveCountry(getCountry(isCurrent = true), false)
        filtersLocalStorageSave.saveRegion(getRegion(isCurrent = true), false)
        filtersLocalStorageSave.saveIndustry(getIndustry(isCurrent = true), false)
        filtersLocalStorageSave.saveSalary(getSalary(isCurrent = true), false)
        filtersLocalStorageSave.saveSalaryFlag(getSalaryFlag(isCurrent = true), false)
    }

    override fun getCountry(isCurrent: Boolean): Area? = filtersLocalStorage.getCountry(isCurrent)
    override fun getRegion(isCurrent: Boolean): Area? = filtersLocalStorage.getRegion(isCurrent)
    override fun getIndustry(isCurrent: Boolean): Industry? = filtersLocalStorage.getIndustry(isCurrent)
    override fun getSalary(isCurrent: Boolean): Int? = filtersLocalStorage.getSalary(isCurrent)
    override fun getSalaryFlag(isCurrent: Boolean): Boolean? = filtersLocalStorage.getSalaryFlag(isCurrent)

}
