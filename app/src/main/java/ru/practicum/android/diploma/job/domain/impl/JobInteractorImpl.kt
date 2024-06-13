package ru.practicum.android.diploma.job.domain.impl

import ru.practicum.android.diploma.job.domain.ExternalNavigator
import ru.practicum.android.diploma.job.domain.JobInteractor

class JobInteractorImpl(
    private val navigation: ExternalNavigator
) : JobInteractor {
    override fun shareLink(alternateUrl: String) {
        navigation.shareLink(alternateUrl)
    }

    override fun emailTo(email: String) {
        navigation.emailTo(email)
    }

    override fun callTo(phoneNumber: String) {
        navigation.callTo(phoneNumber)
    }
}
