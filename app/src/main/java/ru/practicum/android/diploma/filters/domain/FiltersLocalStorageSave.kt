package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersLocalStorageSave {

    fun saveCountry(country: Area?, isCurrent: Boolean)
    fun saveRegion(region: Area?, isCurrent: Boolean)
    fun saveIndustry(industry: Industry?, isCurrent: Boolean)
    fun saveSalary(salary: Int?, isCurrent: Boolean)
    fun saveSalaryFlag(flag: Boolean?, isCurrent: Boolean)
}
