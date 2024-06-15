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

class JobViewModel(
    private val jobInteractor: JobInteractor
) : ViewModel() {

    private val _state = MutableLiveData<JobScreenState>()
    val state: LiveData<JobScreenState> get() = _state

    init {
        _state.value = JobScreenState.Loading
        loadData()
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

    private fun loadData() {
        viewModelScope.launch {
//            delay(2000) // для проверки
            val success = withContext(Dispatchers.IO) {
                true
            }

            if (success) {
                _state.value = JobScreenState.Content
            } else {
                _state.value = JobScreenState.Error
            }
        }
    }
}
