package ru.practicum.android.diploma.job.domain

interface JobRepository {
//    fun loadData()

    suspend fun addFavoriteVacancy() // допистаь

    suspend fun deleteFavoriteVacancy(vacancyId: String) // дописать

    fun favoriteVacancy() // дописать

    fun setupListener(listener: FavoriteListener)

    suspend fun checkLikeVacancy(vacancyId: String) : Boolean
}
