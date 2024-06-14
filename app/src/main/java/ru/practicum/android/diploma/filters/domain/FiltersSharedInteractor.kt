package ru.practicum.android.diploma.filters.domain

interface FiltersSharedInteractor {
    fun saveCountry(country: Int?)
    fun saveRegion(region: Int?)
    fun saveIndustry(industry: Int?)
    fun getCountry(): Int?
    fun getRegion(): Int?
    fun getIndustry(): Int?
}
