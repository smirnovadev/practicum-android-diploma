package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.filters.data.FiltersLocalStorage
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersSharedInteractorImpl(
    private val filtersLocalStorage: FiltersLocalStorage
) : FiltersSharedInteractor {
    override fun applyFilter() {
        filtersLocalStorage.saveCountry(getCurrentCountry())
        filtersLocalStorage.saveRegion(getCurrentRegion())
        filtersLocalStorage.saveIndustry(getCurrentIndustry())
        filtersLocalStorage.saveSalary(getCurrentSalary())
        filtersLocalStorage.saveSalaryFlag(getCurrentSalaryFlag())
    }

    override fun saveCountry(country: Area?) {
        filtersLocalStorage.saveCountry(country)
    }

    override fun saveRegion(region: Area?) {
        filtersLocalStorage.saveRegion(region)
    }

    override fun saveIndustry(industry: Industry?) {
        filtersLocalStorage.saveIndustry(industry)
    }

    override fun saveSalary(salary: Int?) {
        filtersLocalStorage.saveSalary(salary)
    }

    override fun saveSalaryFlag(flag: Boolean?) {
        filtersLocalStorage.saveSalaryFlag(flag)
    }


    override fun saveCurrentCountry(country: Area?) {
        filtersLocalStorage.saveCurrentCountry(country)
    }

    override fun saveCurrentRegion(region: Area?) {
        filtersLocalStorage.saveCurrentRegion(region)
    }

    override fun saveCurrentIndustry(industry: Industry?) {
        filtersLocalStorage.saveCurrentIndustry(industry)
    }

    override fun saveCurrentSalary(salary: Int?) {
        filtersLocalStorage.saveCurrentSalary(salary)
    }

    override fun saveCurrentSalaryFlag(flag: Boolean?) {
        filtersLocalStorage.saveCurrentSalaryFlag(flag)
    }


    override fun getCountry(): Area? = filtersLocalStorage.getCountry()
    override fun getRegion(): Area? = filtersLocalStorage.getRegion()
    override fun getIndustry(): Industry? = filtersLocalStorage.getIndustry()
    override fun getSalary(): Int? = filtersLocalStorage.getSalary()
    override fun getSalaryFlag(): Boolean? = filtersLocalStorage.getSalaryFlag()

    override fun getCurrentCountry(): Area? = filtersLocalStorage.getCurrentCountry()
    override fun getCurrentRegion(): Area? = filtersLocalStorage.getCurrentRegion()
    override fun getCurrentIndustry(): Industry? = filtersLocalStorage.getCurrentIndustry()
    override fun getCurrentSalary(): Int? = filtersLocalStorage.getCurrentSalary()
    override fun getCurrentSalaryFlag(): Boolean? = filtersLocalStorage.getCurrentSalaryFlag()
}
