package ru.practicum.android.diploma.filters.domain

import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

interface FiltersLocalStorage {

    fun getCountry(isCurrent: Boolean): Area?
    fun getRegion(isCurrent: Boolean): Area?
    fun getIndustry(isCurrent: Boolean): Industry?
    fun getSalary(isCurrent: Boolean): Int?
    fun getSalaryFlag(isCurrent: Boolean): Boolean?

}
