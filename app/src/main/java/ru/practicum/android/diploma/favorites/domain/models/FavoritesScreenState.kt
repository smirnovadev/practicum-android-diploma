package ru.practicum.android.diploma.favorites.domain.models

import ru.practicum.android.diploma.db.entities.VacancyEntity

sealed class FavoritesScreenState {
    data class Default(
        val vacancyList: List<VacancyEntity>
    ) : FavoritesScreenState()

    data object NoFavoritesAdded : FavoritesScreenState()
    data object LoadingError : FavoritesScreenState()
}
