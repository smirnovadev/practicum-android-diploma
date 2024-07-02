package ru.practicum.android.diploma.filters.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersViewModel(private val sharedInteractor: FiltersSharedInteractor) : ViewModel() {

    private val hasChangesML = MutableLiveData<Boolean>()
    fun hasChangesLD() = hasChangesML as LiveData<Boolean>

    fun applyFilter(){
        sharedInteractor.applyFilter()
    }

    fun getCurrentCountry(): String = (sharedInteractor.getCurrentCountry() ?: EMPTY_AREA).name
    fun getCurrentRegion(): String = (sharedInteractor.getCurrentRegion() ?: EMPTY_AREA).name
    fun getCurrentIndustry(): String = (sharedInteractor.getCurrentIndustry() ?: EMPTY_INDUSTRY).name
    fun getCurrentSalary(): String = sharedInteractor.getCurrentSalary()?.toString() ?: ""
    fun getCurrentSalaryFlag(): Boolean = sharedInteractor.getCurrentSalaryFlag() ?: DEFAULT_SALARY_FLAG
    fun getCurrentSalaryFlagN(): Boolean? = sharedInteractor.getCurrentSalaryFlag()

    fun getCountryName(): String = (sharedInteractor.getCountry() ?: EMPTY_AREA).name
    fun getRegionName(): String = (sharedInteractor.getRegion() ?: EMPTY_AREA).name
    fun getIndustryName(): String = (sharedInteractor.getIndustry() ?: EMPTY_INDUSTRY).name
    fun getSalary(): String = sharedInteractor.getSalary()?.toString() ?: ""
    fun getSalaryFlag(): Boolean = sharedInteractor.getSalaryFlag() ?: DEFAULT_SALARY_FLAG

    fun comparisonChanges() {
        var discrepancy = false
        val currentCountry = getCurrentCountry()
        val savedCountry = getCountryName()

        if (currentCountry != savedCountry) discrepancy = true

        val currentRegion = getCurrentRegion()
        val savedRegion = getRegionName()

        if (currentRegion != savedRegion) discrepancy = true

        val currentIndustry = getCurrentIndustry()
        val savedIndustry = getIndustryName()

        if (currentIndustry != savedIndustry) discrepancy = true

        val currentSalary = getCurrentSalary()
        val savedSalary = getSalary()

        if (currentSalary != savedSalary) discrepancy = true

        val currentSalaryFlag = getCurrentSalaryFlag()
        val savedSalaryFlag = getSalaryFlag()

        if (currentSalaryFlag != savedSalaryFlag) discrepancy = true

        Log.i("comparisonChanges", discrepancy.toString())

        hasChangesML.postValue(discrepancy)
    }

    fun saveSalary(salary: String?) = sharedInteractor.saveCurrentSalary(salary?.toIntOrNull())
    fun saveSalaryFlag(flag: Boolean) = sharedInteractor.saveCurrentSalaryFlag(flag)

    fun clearCountryName() {
        sharedInteractor.saveCountry(null)
        sharedInteractor.saveCurrentCountry(null)
    }

    fun clearRegionName() {
        sharedInteractor.saveRegion(null)
        sharedInteractor.saveCurrentRegion(null)
    }

    fun clearIndustryName() {
        sharedInteractor.saveIndustry(null)
        sharedInteractor.saveCurrentIndustry(null)
    }
    fun clearSalary() {
        sharedInteractor.saveSalary(null)
        sharedInteractor.saveCurrentSalary(null)
    }
    fun clearSalaryFlag() {
        sharedInteractor.saveSalaryFlag(null)
        sharedInteractor.saveCurrentSalaryFlag(null)
    }

    companion object {
        const val DEFAULT_SALARY_FLAG = false
        val EMPTY_AREA = Area(-1, "")
        val EMPTY_INDUSTRY = Industry(-1, "")
    }
}
