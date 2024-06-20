package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState
import ru.practicum.android.diploma.search.domain.model.Vacancy

interface FavoritesInteractor {
    suspend fun loadData(): FavoritesScreenState
    suspend fun getFavVacancyById(id: String): Vacancy
}
