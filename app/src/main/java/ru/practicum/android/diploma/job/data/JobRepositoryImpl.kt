package ru.practicum.android.diploma.job.data

import ru.practicum.android.diploma.job.domain.FavoriteListener
import ru.practicum.android.diploma.job.domain.JobRepository

class JobRepositoryImpl : JobRepository {

    private var listener: FavoriteListener? = null
//    override fun loadData() {
//        /* no-op */
//    }

    override suspend fun addFavoriteVacancy() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteVacancy(vacancyId: String) {
        TODO("Not yet implemented")
    }

    override fun favoriteVacancy() {
        TODO("Not yet implemented")
    }

    override fun setupListener(listener: FavoriteListener) {
        this.listener = listener
    }

    override suspend fun checkLikeVacancy(vacancyId: String): Boolean {
        TODO("Not yet implemented")
    }
}
