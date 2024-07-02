package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersSharedInteractorImpl(
    private val filtersLocalStorage: FiltersLocalStorage
) : FiltersSharedInteractor {
    override fun applyFilter() {
        filtersLocalStorage.saveCountry(getCountry(isCurrent = true), false)
        filtersLocalStorage.saveRegion(getRegion(isCurrent = true), false)
        filtersLocalStorage.saveIndustry(getIndustry(isCurrent = true), false)
        filtersLocalStorage.saveSalary(getSalary(isCurrent = true), false)
        filtersLocalStorage.saveSalaryFlag(getSalaryFlag(isCurrent = true), false)
    }

    override fun saveCountry(country: Area?, isCurrent: Boolean) {
        filtersLocalStorage.saveCountry(country, isCurrent)
    }

    override fun saveRegion(region: Area?, isCurrent: Boolean) {
        filtersLocalStorage.saveRegion(region, isCurrent)
    }

    override fun saveIndustry(industry: Industry?, isCurrent: Boolean) {
        filtersLocalStorage.saveIndustry(industry, isCurrent)
    }

    override fun saveSalary(salary: Int?, isCurrent: Boolean) {
        filtersLocalStorage.saveSalary(salary, isCurrent)
    }

    override fun saveSalaryFlag(flag: Boolean?, isCurrent: Boolean) {
        filtersLocalStorage.saveSalaryFlag(flag, isCurrent)
    }

    override fun getCountry(isCurrent: Boolean): Area? = filtersLocalStorage.getCountry(isCurrent)
    override fun getRegion(isCurrent: Boolean): Area? = filtersLocalStorage.getRegion(isCurrent)
    override fun getIndustry(isCurrent: Boolean): Industry? = filtersLocalStorage.getIndustry(isCurrent)
    override fun getSalary(isCurrent: Boolean): Int? = filtersLocalStorage.getSalary(isCurrent)
    override fun getSalaryFlag(isCurrent: Boolean): Boolean? = filtersLocalStorage.getSalaryFlag(isCurrent)

}
