package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersSharedInteractor {

    fun applyFilter()
    fun saveCurrentCountry(country: Area?)
    fun saveCurrentRegion(region: Area?)
    fun saveCurrentIndustry(industry: Industry?)
    fun saveCurrentSalary(salary: Int?)
    fun saveCurrentSalaryFlag(flag: Boolean?)
    fun saveCountry(country: Area?)
    fun saveRegion(region: Area?)
    fun saveIndustry(industry: Industry?)
    fun saveSalary(salary: Int?)
    fun saveSalaryFlag(flag: Boolean?)
    fun getCountry(): Area?
    fun getRegion(): Area?
    fun getIndustry(): Industry?
    fun getSalary(): Int?
    fun getSalaryFlag(): Boolean?
    fun getCurrentCountry(): Area?
    fun getCurrentRegion(): Area?
    fun getCurrentIndustry(): Industry?
    fun getCurrentSalary(): Int?
    fun getCurrentSalaryFlag(): Boolean?
}
