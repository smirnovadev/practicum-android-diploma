package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.filters.data.FiltersLocalStorage
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersSharedInteractorImpl(
    private val filtersLocalStorage: FiltersLocalStorage
) : FiltersSharedInteractor {
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

    override fun getCountry(): Area? = filtersLocalStorage.getCountry()

    override fun getRegion(): Area? = filtersLocalStorage.getRegion()

    override fun getIndustry(): Industry? = filtersLocalStorage.getIndustry()

    override fun getSalary(): Int? = filtersLocalStorage.getSalary()
    override fun getSalaryFlag(): Boolean? = filtersLocalStorage.getSalaryFlag()

    override fun deleteIndustry() {
        filtersLocalStorage.deleteIndustry()
    }

    override fun deleteCountry() {
        filtersLocalStorage.deleteCountry()
    }

    override fun deleteRegion() {
        filtersLocalStorage.deleteRegion()
    }

    override fun deleteSalary() {
        filtersLocalStorage.deleteSalary()
    }

    override fun deleteSalaryFlag() {
        filtersLocalStorage.deleteSalaryFlag()
    }
}
