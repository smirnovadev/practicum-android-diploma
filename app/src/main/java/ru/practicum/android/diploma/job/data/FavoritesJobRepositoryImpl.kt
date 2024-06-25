package ru.practicum.android.diploma.job.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.db.AppDatabase
import ru.practicum.android.diploma.job.domain.FavoritesJobRepository
import ru.practicum.android.diploma.job.domain.JobDbConvertor
import ru.practicum.android.diploma.search.domain.model.Vacancy

class FavoritesJobRepositoryImpl(
    private val appDataBase: AppDatabase,
    private val jobDbConvertor: JobDbConvertor
) : FavoritesJobRepository {
    override suspend fun addFavoriteJob(vacancy: Vacancy) {
        appDataBase.vacanciesDao().insertVacancy(jobDbConvertor.map(vacancy))
    }

    override suspend fun deleteFavoriteJob(vacancy: Vacancy) {
        appDataBase.vacanciesDao().deleteVacancy(jobDbConvertor.map(vacancy))
    }

    override suspend fun getAllFavoriteVacancies(): Flow<List<Vacancy>> = flow {
        val favoriteVacancies = appDataBase.vacanciesDao().getAllFavoriteVacancies()
        emit(jobDbConvertor.convertToListVacancy(favoriteVacancies))
    }

    override suspend fun getFavoriteVacancyById(id: String): Flow<Vacancy?> = flow {
        val favoriteVacancies = appDataBase.vacanciesDao().getAllFavoriteVacancies()
        favoriteVacancies
            .firstOrNull { it.id == id }
            ?.let {
                jobDbConvertor.mapToVacancy(it)
            }
            .also { emit(it) }
    }
}
