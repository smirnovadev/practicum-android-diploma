package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState

interface FavoritesInteractor {
    suspend fun loadData(): FavoritesScreenState
}
