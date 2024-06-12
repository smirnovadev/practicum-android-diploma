package ru.practicum.android.diploma.job.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.JobScreenState

class JobViewModel(
    private val jobInteractor: JobInteractor
) : ViewModel() {

//    init {
//        listen()
//    }

    private val _likeState = MutableLiveData<Boolean>()
    val likeState: LiveData<Boolean> = _likeState

    private val stateJob = MutableLiveData<JobScreenState>()
    fun observeState(): LiveData<JobScreenState> = stateJob

    fun shareLink(alternateUrl: String) {
        jobInteractor.shareLink(alternateUrl)
    }

    fun emailTo(email: String) {
        jobInteractor.emailTo(email)
    }

    fun callTo(phoneNumber: String) {
        jobInteractor.callTo(phoneNumber)
    }
}
