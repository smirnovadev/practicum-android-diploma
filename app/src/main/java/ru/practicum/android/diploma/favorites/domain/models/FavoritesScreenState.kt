package ru.practicum.android.diploma.favorites.domain.models

import ru.practicum.android.diploma.db.entities.VacancyEntity

sealed class FavoritesScreenState {
    data class Default(val vacancyList: List<VacancyEntity>) : FavoritesScreenState() // доработать, когда будет готова база данных
    data object NoFavoritesAdded : FavoritesScreenState()
    data object LoadingError : FavoritesScreenState()

}
