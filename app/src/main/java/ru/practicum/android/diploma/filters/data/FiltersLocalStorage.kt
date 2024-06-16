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
            if (country == null)
                remove(COUNTRY_KEY)
            else
                putString(COUNTRY_KEY, gson.toJson(country))
        }.apply()
    }

    fun saveRegion(region: Area?) {
        sharedPreferences.edit().apply {
            if (region == null)
                remove(REGION_KEY)
            else
                putString(REGION_KEY, gson.toJson(region))
        }.apply()
    }

    fun saveIndustry(industry: Industry?) {
        sharedPreferences.edit().apply {
            if (industry == null)
                remove(INDUSTRY_KEY)
            else
                putString(INDUSTRY_KEY, gson.toJson(industry))
        }.apply()
    }

    fun saveSalary(salary: Int?) {
        sharedPreferences.edit().apply {
            if (salary == null)
                remove(SALARY_KEY)
            else
                putInt(SALARY_KEY, salary)
        }.apply()
    }

    fun saveSalaryFlag(flag: Boolean?) {
        sharedPreferences.edit().apply {
            if (flag == null)
                remove(SALARY_FLAG_KEY)
            else
                putBoolean(SALARY_FLAG_KEY, flag)
        }.apply()
    }

    fun getCountry(): Area? {
        val s = sharedPreferences.getString(COUNTRY_KEY, null) ?: return null
        return gson.fromJson(s, Area::class.java)
    }

    fun getRegion(): Area? {
        val s = sharedPreferences.getString(REGION_KEY, null) ?: return null
        return gson.fromJson(s, Area::class.java)
    }

    fun getIndustry(): Industry? {
        val s = sharedPreferences.getString(INDUSTRY_KEY, null) ?: return null
        return gson.fromJson(s, Industry::class.java)
    }

    fun getSalary(): Int? {
        val s = sharedPreferences.getInt(SALARY_KEY, -1)
        return if (s == -1) null else s
    }

    fun getSalaryFlag(): Boolean? {
        if (sharedPreferences.contains(SALARY_FLAG_KEY))
            return sharedPreferences.getBoolean(SALARY_FLAG_KEY, false)
        return null
    }

    fun deleteCountry() {
        sharedPreferences.edit().apply {
            remove(COUNTRY_KEY)
        }.apply()
    }

    fun deleteRegion() {
        sharedPreferences.edit().apply {
            remove(REGION_KEY)
        }.apply()
    }

    fun deleteIndustry() {
        sharedPreferences.edit().apply {
            remove(INDUSTRY_KEY)
        }.apply()
    }

    fun deleteSalary() {
        sharedPreferences.edit().apply {
            remove(SALARY_KEY)
        }.apply()
    }

    fun deleteSalaryFlag() {
        sharedPreferences.edit().apply {
            remove(SALARY_FLAG_KEY)
        }.apply()
    }

    private companion object {
        const val COUNTRY_KEY = "filters_country"
        const val REGION_KEY = "filters_region"
        const val INDUSTRY_KEY = "filters_industry"
        const val SALARY_KEY = "filters_salary"
        const val SALARY_FLAG_KEY = "filters_salary_flag"
    }
}
