package ru.practicum.android.diploma.job.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.favorites.domain.FavoritesInteractor
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.JobScreenState
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy

class JobViewModel(
    private val jobInteractor: JobInteractor,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {

    private val screenState = MutableLiveData<JobScreenState>()
    fun getScreenState(): LiveData<JobScreenState> = screenState

    private val isFavoriteVacancyLiveData = MutableLiveData<Boolean>()
    fun isFavoriteVacancyLiveData(): LiveData<Boolean> = isFavoriteVacancyLiveData

    init {
        screenState.postValue(JobScreenState.Loading)
    }

    fun onFavoriteClicked(vacancy: Vacancy?) {
        vacancy ?: error("vacancy is null")
        viewModelScope.launch {
            if (isFavoriteVacancyLiveData.value == true) {
                jobInteractor.deleteFavorite(vacancy)
                isFavoriteVacancyLiveData.postValue(false)
            } else {
                jobInteractor.addFavorite(vacancy)
                isFavoriteVacancyLiveData.postValue(true)
            }
        }
    }

    fun loadFavoriteState(vacancyId: String?) {
        vacancyId ?: error("vacancyId is null")
        viewModelScope.launch {
            jobInteractor.isFavoriteVacancy(vacancyId).collect { isFavorite ->
                isFavoriteVacancyLiveData.value = isFavorite
            }
        }
    }

    fun shareLink(alternateUrl: String) {
        jobInteractor.shareLink(alternateUrl)
    }

    fun emailTo(email: String) {
        jobInteractor.emailTo(email)
    }

    fun callTo(phoneNumber: String) {
        jobInteractor.callTo(phoneNumber)
    }

    fun searchVacancyById(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                jobInteractor.getVacancyById(id)
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> screenState.postValue(JobScreenState.Content(resource.data!!))
                            is Resource.Error -> screenState.postValue(JobScreenState.Error)
                        }
                    }
            }
        }
    }

    fun getFavoriteVacancyById(vacancyId: String) {
        viewModelScope.launch {
            val vacancy = favoritesInteractor.getFavVacancyById(vacancyId)
            screenState.postValue(JobScreenState.Content(vacancy))
        }
    }
}
