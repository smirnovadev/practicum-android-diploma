package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.filters.data.FiltersLocalStorage
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersSharedInteractorImpl(
    private val filtersLocalStorage: FiltersLocalStorage
) : FiltersSharedInteractor {
    override fun applyFilter() {
        filtersLocalStorage.saveCountry(getCurrentCountry(), false)
        filtersLocalStorage.saveRegion(getCurrentRegion(), false)
        filtersLocalStorage.saveIndustry(getCurrentIndustry(), false)
        filtersLocalStorage.saveSalary(getCurrentSalary(), false)
        filtersLocalStorage.saveSalaryFlag(getCurrentSalaryFlag(), false)
    }

    override fun saveCountry(country: Area?) {
        filtersLocalStorage.saveCountry(country, false)
    }

    override fun saveRegion(region: Area?) {
        filtersLocalStorage.saveRegion(region, false)
    }

    override fun saveIndustry(industry: Industry?) {
        filtersLocalStorage.saveIndustry(industry, false)
    }

    override fun saveSalary(salary: Int?) {
        filtersLocalStorage.saveSalary(salary, false)
    }

    override fun saveSalaryFlag(flag: Boolean?) {
        filtersLocalStorage.saveSalaryFlag(flag, false)
    }

    override fun saveCurrentCountry(country: Area?) {
        filtersLocalStorage.saveCountry(country, true)
    }

    override fun saveCurrentRegion(region: Area?) {
        filtersLocalStorage.saveRegion(region, true)
    }

    override fun saveCurrentIndustry(industry: Industry?) {
        filtersLocalStorage.saveIndustry(industry, true)
    }

    override fun saveCurrentSalary(salary: Int?) {
        filtersLocalStorage.saveSalary(salary, true)
    }

    override fun saveCurrentSalaryFlag(flag: Boolean?) {
        filtersLocalStorage.saveSalaryFlag(flag, true)
    }

    override fun getCountry(): Area? = filtersLocalStorage.getCountry(false)
    override fun getRegion(): Area? = filtersLocalStorage.getRegion(false)
    override fun getIndustry(): Industry? = filtersLocalStorage.getIndustry(false)
    override fun getSalary(): Int? = filtersLocalStorage.getSalary(false)
    override fun getSalaryFlag(): Boolean? = filtersLocalStorage.getSalaryFlag(false)

    override fun getCurrentCountry(): Area? = filtersLocalStorage.getCountry(true)
    override fun getCurrentRegion(): Area? = filtersLocalStorage.getRegion(true)
    override fun getCurrentIndustry(): Industry? = filtersLocalStorage.getIndustry(true)
    override fun getCurrentSalary(): Int? = filtersLocalStorage.getSalary(true)
    override fun getCurrentSalaryFlag(): Boolean? = filtersLocalStorage.getSalaryFlag(true)
}
