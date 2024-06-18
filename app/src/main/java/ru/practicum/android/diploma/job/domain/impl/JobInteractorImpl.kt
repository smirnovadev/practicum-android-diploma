package ru.practicum.android.diploma.job.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import ru.practicum.android.diploma.job.domain.ExternalNavigator
import ru.practicum.android.diploma.job.domain.FavoritesJobRepository
import ru.practicum.android.diploma.job.domain.JobInteractor
import ru.practicum.android.diploma.job.domain.JobRepository
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy

class JobInteractorImpl(
    private val navigation: ExternalNavigator,
    private val jobRepository: JobRepository,
    private val favoritesJobRepository: FavoritesJobRepository
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

    override suspend fun deleteFavorite(vacancy: Vacancy) {
        return favoritesJobRepository.deleteFavoriteJob(vacancy)
    }

    override suspend fun addFavorite(vacancy: Vacancy) {
        return favoritesJobRepository.addFavoriteJob(vacancy)
    }

    override suspend fun getAllFavorite(): Flow<List<Vacancy>> {
        return favoritesJobRepository.getAllFavoriteVacancies()
    }

    override suspend fun isFavoriteVacancy(id: String): Flow<Boolean> {
        return favoritesJobRepository.getFavoriteVacancyById(id).map { vacancy ->
            vacancy != null
        }
    }

    override fun getVacancyById(id: String): Flow<Resource<Vacancy>> {
        return jobRepository.getVacancyById(id)
    }
}
