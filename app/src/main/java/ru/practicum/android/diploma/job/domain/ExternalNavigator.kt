package ru.practicum.android.diploma.job.domain

interface ExternalNavigator {
    fun emailTo(email: String)
    fun callTo(phoneNumber: String)
    fun shareLink(alternateUrl: String)
}
