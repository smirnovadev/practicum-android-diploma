package ru.practicum.android.diploma.job.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.JobScreenState
import ru.practicum.android.diploma.search.domain.model.Resource

class JobViewModel(
    private val jobInteractor: JobInteractor
) : ViewModel() {

    private val screenState = MutableLiveData<JobScreenState>()
    fun getScreenState(): LiveData<JobScreenState> = screenState

    init {
        screenState.postValue(JobScreenState.Loading)
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
}
