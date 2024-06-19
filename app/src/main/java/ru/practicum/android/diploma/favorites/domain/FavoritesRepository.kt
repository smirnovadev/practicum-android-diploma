package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.search.domain.model.Vacancy

interface FavoritesRepository {
    suspend fun loadData(): ArrayList<Vacancy>
    suspend fun getFavVacancyById(id: String): Vacancy
}
