package ru.practicum.android.diploma.job.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Vacancy

interface FavoritesJobRepository {
    suspend fun addFavoriteJob(vacancy: Vacancy)

    suspend fun deleteFavoriteJob(vacancy: Vacancy)

    suspend fun getAllFavoriteVacancies(): Flow<List<Vacancy>>

    suspend fun getFavoriteVacancyById(id: String): Flow<Vacancy?>
}
