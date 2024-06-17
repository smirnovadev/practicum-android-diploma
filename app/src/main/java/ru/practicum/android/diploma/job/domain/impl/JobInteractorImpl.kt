package ru.practicum.android.diploma.job.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.job.domain.ExternalNavigator
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.JobRepository
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy

class JobInteractorImpl(
    private val navigation: ExternalNavigator,
    private val jobRepository: JobRepository,
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

    override fun getVacancyById(id: String): Flow<Resource<Vacancy>> {
        return jobRepository.getVacancyById(id)
    }
}
