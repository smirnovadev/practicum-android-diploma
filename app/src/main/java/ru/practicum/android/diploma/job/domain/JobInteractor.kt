package ru.practicum.android.diploma.job.domain

interface JobInteractor {

    fun shareLink(alternateUrl: String)
    fun emailTo(email: String)
    fun callTo(phoneNumber: String)
}
