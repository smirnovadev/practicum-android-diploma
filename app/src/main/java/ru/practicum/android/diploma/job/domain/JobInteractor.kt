package ru.practicum.android.diploma.job.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Resource
import ru.practicum.android.diploma.search.domain.model.Vacancy

interface JobInteractor {

    fun shareLink(alternateUrl: String)
    fun emailTo(email: String)
    fun callTo(phoneNumber: String)
    suspend fun deleteFavorite(vacancy: Vacancy)
    suspend fun addFavorite(vacancy: Vacancy)
    suspend fun getAllFavorite(): Flow<List<Vacancy>>
    suspend fun isFavoriteVacancy(id: String): Flow<Boolean>

    fun getVacancyById(id: String): Flow<Resource<Vacancy>>
}
