package ru.practicum.android.diploma.filters.data

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersLocalStorage(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    fun saveCountry(country: Area?) {
        sharedPreferences.edit().apply {
            if (country == null) {
                remove(COUNTRY_KEY)
            } else {
                putString(COUNTRY_KEY, gson.toJson(country))
            }
        }.apply()
    }

    fun saveRegion(region: Area?) {
        sharedPreferences.edit().apply {
            if (region == null) {
                remove(REGION_KEY)
            } else {
                putString(REGION_KEY, gson.toJson(region))
            }
        }.apply()
    }

    fun saveIndustry(industry: Industry?) {
        sharedPreferences.edit().apply {
            if (industry == null) {
                remove(INDUSTRY_KEY)
            } else {
                putString(INDUSTRY_KEY, gson.toJson(industry))
            }
        }.apply()
    }

    fun saveSalary(salary: Int?) {
        sharedPreferences.edit().apply {
            if (salary == null) {
                remove(SALARY_KEY)
            } else {
                putInt(SALARY_KEY, salary)
            }
        }.apply()
    }

    fun saveSalaryFlag(flag: Boolean?) {
        sharedPreferences.edit().apply {
            if (flag == null) {
                remove(SALARY_FLAG_KEY)
            } else {
                putBoolean(SALARY_FLAG_KEY, flag)
            }
        }.apply()
    }

    fun getCountry(): Area? {
        if (sharedPreferences.contains(COUNTRY_KEY))
            return gson.fromJson(
                sharedPreferences.getString(COUNTRY_KEY, null),
                Area::class.java
            )
        return null
    }

    fun getRegion(): Area? {
        if (sharedPreferences.contains(REGION_KEY))
            return gson.fromJson(
                sharedPreferences.getString(REGION_KEY, null),
                Area::class.java
            )
        return null
    }

    fun getIndustry(): Industry? {
        if (sharedPreferences.contains(INDUSTRY_KEY))
            return gson.fromJson(
                sharedPreferences.getString(INDUSTRY_KEY, null),
                Industry::class.java
            )
        return null
    }

    fun getSalary(): Int? {
        if (sharedPreferences.contains(SALARY_KEY)) {
            return sharedPreferences.getInt(SALARY_KEY, -1)
        }
        return null
    }

    fun getSalaryFlag(): Boolean? {
        if (sharedPreferences.contains(SALARY_FLAG_KEY)) {
            return sharedPreferences.getBoolean(SALARY_FLAG_KEY, false)
        }
        return null
    }

    private companion object {
        const val COUNTRY_KEY = "filters_country"
        const val REGION_KEY = "filters_region"
        const val INDUSTRY_KEY = "filters_industry"
        const val SALARY_KEY = "filters_salary"
        const val SALARY_FLAG_KEY = "filters_salary_flag"
    }
}
