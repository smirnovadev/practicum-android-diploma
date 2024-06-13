package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.db.entities.VacancyWithEmployer

interface FavoritesRepository {
    suspend fun loadData(): List<VacancyWithEmployer>
}
