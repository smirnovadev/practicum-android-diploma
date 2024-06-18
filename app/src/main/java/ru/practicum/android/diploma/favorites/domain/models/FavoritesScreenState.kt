package ru.practicum.android.diploma.favorites.domain.models

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed class FavoritesScreenState {
    data class Default(
        val vacancyList: ArrayList<Vacancy>
    ) : FavoritesScreenState()

    data object NoFavoritesAdded : FavoritesScreenState()
    data object LoadingError : FavoritesScreenState()
}
