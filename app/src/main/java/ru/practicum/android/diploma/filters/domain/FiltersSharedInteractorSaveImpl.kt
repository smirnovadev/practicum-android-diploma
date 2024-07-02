package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersSharedInteractorSaveImpl(
    private val filtersLocalStorageSave: FiltersLocalStorageSave
) : FiltersSharedInteractorSave {
    override fun saveCountry(country: Area?, isCurrent: Boolean) {
        filtersLocalStorageSave.saveCountry(country, isCurrent)
    }

    override fun saveRegion(region: Area?, isCurrent: Boolean) {
        filtersLocalStorageSave.saveRegion(region, isCurrent)
    }

    override fun saveIndustry(industry: Industry?, isCurrent: Boolean) {
        filtersLocalStorageSave.saveIndustry(industry, isCurrent)
    }

    override fun saveSalary(salary: Int?, isCurrent: Boolean) {
        filtersLocalStorageSave.saveSalary(salary, isCurrent)
    }

    override fun saveSalaryFlag(flag: Boolean?, isCurrent: Boolean) {
        filtersLocalStorageSave.saveSalaryFlag(flag, isCurrent)
    }

}
