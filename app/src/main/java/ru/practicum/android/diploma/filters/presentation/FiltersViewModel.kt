package ru.practicum.android.diploma.filters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filters.domain.FiltersSharedInteractor
import ru.practicum.android.diploma.filters.domain.models.Filters
import ru.practicum.android.diploma.filters.domain.models.FiltersApplyButtonState
import ru.practicum.android.diploma.filters.domain.models.FiltersResetButtonState
import ru.practicum.android.diploma.filters.domain.models.FiltersScreenState
import ru.practicum.android.diploma.search.domain.model.fields.Area
import ru.practicum.android.diploma.search.domain.model.fields.Industry

class FiltersViewModel(private val sharedInteractor: FiltersSharedInteractor) : ViewModel() {

    private var currentFilters: Filters = getCurrentFilters()
    private var appliedFilters: Filters = getAppliedFiltes()

    private val screenState = MutableLiveData<FiltersScreenState>()
    fun getScreenState(): LiveData<FiltersScreenState> = screenState

    private val applyButtonState = MutableLiveData<FiltersApplyButtonState>()
    fun getApplyButtonState(): LiveData<FiltersApplyButtonState> = applyButtonState

    private val resetButtonState = MutableLiveData<FiltersResetButtonState>()
    fun getResetButtonState(): LiveData<FiltersResetButtonState> = resetButtonState

    init {
        updateFilters()
    }

    private fun getAppliedFiltes(): Filters {
        return Filters(
            (sharedInteractor.getCountry(isCurrent = false) ?: EMPTY_AREA).name,
            (sharedInteractor.getRegion(isCurrent = false) ?: EMPTY_AREA).name,
            (sharedInteractor.getIndustry(isCurrent = false) ?: EMPTY_INDUSTRY).name,
            sharedInteractor.getSalary(isCurrent = false)?.toString() ?: EMPTY_STRING,
            sharedInteractor.getSalaryFlag(isCurrent = false) ?: DEFAULT_SALARY_FLAG
        )
    }

    private fun getCurrentFilters(): Filters {
        return Filters(
            (sharedInteractor.getCountry(isCurrent = true) ?: EMPTY_AREA).name,
            (sharedInteractor.getRegion(isCurrent = true) ?: EMPTY_AREA).name,
            (sharedInteractor.getIndustry(isCurrent = true) ?: EMPTY_INDUSTRY).name,
            sharedInteractor.getSalary(isCurrent = true)?.toString() ?: EMPTY_STRING,
            sharedInteractor.getSalaryFlag(isCurrent = true) ?: DEFAULT_SALARY_FLAG
        )
    }

    fun updateFilters() {
        currentFilters = getCurrentFilters()
        appliedFilters = getAppliedFiltes()
        screenState.postValue(FiltersScreenState.Content(currentFilters))

        if (currentFilters == appliedFilters) {
            applyButtonState.postValue(FiltersApplyButtonState.InVisible)
        } else {
            applyButtonState.postValue(FiltersApplyButtonState.Visible)
        }

        if (areFiltersEmpty(appliedFilters)) {
            resetButtonState.postValue(FiltersResetButtonState.InVisible)
        } else {
            resetButtonState.postValue(FiltersResetButtonState.Visible)
        }
    }

    private fun areFiltersEmpty(filters: Filters): Boolean {
        val isEmpty: Boolean =
            filters.country.isEmpty()
                && filters.region.isEmpty()
                && filters.industry.isEmpty()
                && filters.salary.isEmpty()

        return isEmpty && !filters.salaryFlag
    }

    fun applyFilters() {
        sharedInteractor.applyFilter()
        updateFilters()
    }

    fun updateCurrentSalary(salary: String?) {
        sharedInteractor.saveSalary(salary?.toIntOrNull(), isCurrent = true)
        updateFilters()
    }

    fun updateSalaryFlag(flag: Boolean) {
        sharedInteractor.saveSalaryFlag(flag, isCurrent = true)
        updateFilters()
    }

    fun clearRegions() {
        sharedInteractor.saveCountry(null, isCurrent = true)
        sharedInteractor.saveRegion(null, isCurrent = true)
        updateFilters()
    }

    fun clearIndustry() {
        sharedInteractor.saveIndustry(null, isCurrent = true)
        updateFilters()
    }

    fun clearSalary() {
        sharedInteractor.saveSalary(null, isCurrent = true)
        updateFilters()
    }

    fun clearSalaryFlag() {
        sharedInteractor.saveSalaryFlag(null, isCurrent = true)
        updateFilters()
    }

    fun resetFilters() {
        clearRegions()
        sharedInteractor.saveCountry(null, isCurrent = false)
        sharedInteractor.saveRegion(null, isCurrent = false)
        clearIndustry()
        sharedInteractor.saveIndustry(null, isCurrent = false)
        clearSalary()
        sharedInteractor.saveSalary(null, isCurrent = false)
        clearSalaryFlag()
        sharedInteractor.saveSalaryFlag(null, isCurrent = false)
        updateFilters()
    }

    companion object {
        const val DEFAULT_SALARY_FLAG = false
        const val EMPTY_STRING = ""
        val EMPTY_AREA = Area(-1, "")
        val EMPTY_INDUSTRY = Industry(-1, "")
    }
}
