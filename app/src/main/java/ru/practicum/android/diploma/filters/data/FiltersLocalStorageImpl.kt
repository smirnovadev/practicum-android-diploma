package ru.practicum.android.diploma.filters.data

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.filters.domain.FiltersLocalStorage
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersLocalStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : FiltersLocalStorage {
    override fun saveCountry(country: Area?, isCurrent: Boolean) {
        if (isCurrent) {
            sharedPreferences.edit().apply {
                if (country == null) {
                    remove(COUNTRY_KEY_CURRENT)
                } else {
                    putString(COUNTRY_KEY_CURRENT, gson.toJson(country))
                }
            }.apply()
        } else {
            sharedPreferences.edit().apply {
                if (country == null) {
                    remove(COUNTRY_KEY)
                } else {
                    putString(COUNTRY_KEY, gson.toJson(country))
                }
            }.apply()
        }
    }

    override fun saveRegion(region: Area?, isCurrent: Boolean) {
        if (isCurrent) {
            sharedPreferences.edit().apply {
                if (region == null) {
                    remove(REGION_KEY_CURRENT)
                } else {
                    putString(REGION_KEY_CURRENT, gson.toJson(region))
                }
            }.apply()
        } else {
            sharedPreferences.edit().apply {
                if (region == null) {
                    remove(REGION_KEY)
                } else {
                    putString(REGION_KEY, gson.toJson(region))
                }
            }.apply()
        }
    }

    override fun saveIndustry(industry: Industry?, isCurrent: Boolean) {
        if (isCurrent) {
            sharedPreferences.edit().apply {
                if (industry == null) {
                    remove(INDUSTRY_KEY_CURRENT)
                } else {
                    putString(INDUSTRY_KEY_CURRENT, gson.toJson(industry))
                }
            }.apply()
        } else {
            sharedPreferences.edit().apply {
                if (industry == null) {
                    remove(INDUSTRY_KEY)
                } else {
                    putString(INDUSTRY_KEY, gson.toJson(industry))
                }
            }.apply()
        }
    }

    override fun saveSalary(salary: Int?, isCurrent: Boolean) {
        if (isCurrent) {
            sharedPreferences.edit().apply {
                if (salary == null) {
                    remove(SALARY_KEY_CURRENT)
                } else {
                    putInt(SALARY_KEY_CURRENT, salary)
                }
            }.apply()
        } else {
            sharedPreferences.edit().apply {
                if (salary == null) {
                    remove(SALARY_KEY)
                } else {
                    putInt(SALARY_KEY, salary)
                }
            }.apply()
        }
    }

    override fun saveSalaryFlag(flag: Boolean?, isCurrent: Boolean) {
        if (isCurrent) {
            sharedPreferences.edit().apply {
                if (flag == null) {
                    remove(SALARY_FLAG_KEY_CURRENT)
                } else {
                    putBoolean(SALARY_FLAG_KEY_CURRENT, flag)
                }
            }.apply()
        } else {
            sharedPreferences.edit().apply {
                if (flag == null) {
                    remove(SALARY_FLAG_KEY)
                } else {
                    putBoolean(SALARY_FLAG_KEY, flag)
                }
            }.apply()
        }
    }

    override fun getCountry(isCurrent: Boolean): Area? {
        if (isCurrent) {
            return if (sharedPreferences.contains(COUNTRY_KEY_CURRENT)) {
                gson.fromJson(
                    sharedPreferences.getString(COUNTRY_KEY_CURRENT, null),
                    Area::class.java
                )
            } else {
                null
            }
        } else {
            return if (sharedPreferences.contains(COUNTRY_KEY)) {
                gson.fromJson(
                    sharedPreferences.getString(COUNTRY_KEY, null),
                    Area::class.java
                )
            } else {
                null
            }
        }
    }

    override fun getRegion(isCurrent: Boolean): Area? {
        if (isCurrent) {
            return if (sharedPreferences.contains(REGION_KEY_CURRENT)) {
                gson.fromJson(
                    sharedPreferences.getString(REGION_KEY_CURRENT, null),
                    Area::class.java
                )
            } else {
                null
            }
        } else {
            return if (sharedPreferences.contains(REGION_KEY)) {
                gson.fromJson(
                    sharedPreferences.getString(REGION_KEY, null),
                    Area::class.java
                )
            } else {
                null
            }
        }
    }

    override fun getIndustry(isCurrent: Boolean): Industry? {
        return when (isCurrent) {
            true -> {
                if (sharedPreferences.contains(INDUSTRY_KEY_CURRENT)) {
                    gson.fromJson(
                        sharedPreferences.getString(INDUSTRY_KEY_CURRENT, null),
                        Industry::class.java
                    )
                } else {
                    null
                }
            }
            false -> {
                if (sharedPreferences.contains(INDUSTRY_KEY)) {
                    gson.fromJson(
                        sharedPreferences.getString(INDUSTRY_KEY, null),
                        Industry::class.java
                    )
                } else {
                    null
                }
            }
        }
    }

    override fun getSalary(isCurrent: Boolean): Int? {
        return when (isCurrent) {
            true -> {
                if (sharedPreferences.contains(SALARY_KEY_CURRENT)) {
                    sharedPreferences.getInt(SALARY_KEY_CURRENT, -1)
                } else {
                    null
                }
            }
            false -> {
                if (sharedPreferences.contains(SALARY_KEY)) {
                    sharedPreferences.getInt(SALARY_KEY, -1)
                } else {
                    null
                }
            }
        }
    }

    override fun getSalaryFlag(isCurrent: Boolean): Boolean? {
        return when (isCurrent) {
            true -> {
                if (sharedPreferences.contains(SALARY_FLAG_KEY_CURRENT)) {
                    sharedPreferences.getBoolean(SALARY_FLAG_KEY_CURRENT, false)
                } else {
                    null
                }
            }
            false -> {
                if (sharedPreferences.contains(SALARY_FLAG_KEY)) {
                    sharedPreferences.getBoolean(SALARY_FLAG_KEY, false)
                } else {
                    null
                }
            }
        }
    }

    private companion object {
        const val COUNTRY_KEY = "filters_country"
        const val REGION_KEY = "filters_region"
        const val INDUSTRY_KEY = "filters_industry"
        const val SALARY_KEY = "filters_salary"
        const val SALARY_FLAG_KEY = "filters_salary_flag"
        const val COUNTRY_KEY_CURRENT = "filters_country_current"
        const val REGION_KEY_CURRENT = "filters_region_current"
        const val INDUSTRY_KEY_CURRENT = "filters_industry_current"
        const val SALARY_KEY_CURRENT = "filters_salary_current"
        const val SALARY_FLAG_KEY_CURRENT = "filters_salary_flag_current"
    }
}
