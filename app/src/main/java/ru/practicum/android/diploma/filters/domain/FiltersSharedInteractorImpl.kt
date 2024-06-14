package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.filters.data.FiltersLocalStorage

class FiltersSharedInteractorImpl(
    private val filtersLocalStorage: FiltersLocalStorage
) : FiltersSharedInteractor {
    override fun saveCountry(country: Int?) {
        filtersLocalStorage.saveCountry(country)
    }

    override fun saveRegion(region: Int?) {
        filtersLocalStorage.saveRegion(region)
    }

    override fun saveIndustry(industry: Int?) {
        filtersLocalStorage.saveIndustry(industry)
    }

    override fun getCountry(): Int? = filtersLocalStorage.getCountry()

    override fun getRegion(): Int? = filtersLocalStorage.getRegion()

    override fun getIndustry(): Int? = filtersLocalStorage.getIndustry()
}
