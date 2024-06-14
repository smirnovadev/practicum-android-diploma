package ru.practicum.android.diploma.filters.data

import android.content.SharedPreferences

class FiltersLocalStorage(private val sharedPreferences: SharedPreferences) {
    fun saveCountry(country: Int?) {
        sharedPreferences.edit().apply {
            if (country == null)
                remove(COUNTRY_KEY)
            else
                putInt(COUNTRY_KEY, country)
        }.apply()
    }

    fun saveRegion(region: Int?) {
        sharedPreferences.edit().apply {
            if (region == null)
                remove(REGION_KEY)
            else
                putInt(REGION_KEY, region)
        }.apply()
    }

    fun saveIndustry(industry: Int?) {
        sharedPreferences.edit().apply {
            if (industry == null)
                remove(INDUSTRY_KEY)
            else
                putInt(INDUSTRY_KEY, industry)
        }.apply()
    }

    fun getCountry(): Int? {
        val int = sharedPreferences.getInt(COUNTRY_KEY, -1)
        return if (int == -1) null else int
    }

    fun getRegion(): Int? {
        val int = sharedPreferences.getInt(REGION_KEY, -1)
        return if (int == -1) null else int
    }

    fun getIndustry(): Int? {
        val int = sharedPreferences.getInt(INDUSTRY_KEY, -1)
        return if (int == -1) null else int
    }


    private companion object {
        const val COUNTRY_KEY = "filters_country"
        const val REGION_KEY = "filters_region"
        const val INDUSTRY_KEY = "filters_industry"
    }
}
