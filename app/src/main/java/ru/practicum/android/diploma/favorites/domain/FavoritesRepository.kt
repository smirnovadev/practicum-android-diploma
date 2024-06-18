package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.db.entities.VacancyEntity

interface FavoritesRepository {
    suspend fun loadData(): List<VacancyEntity>
}
