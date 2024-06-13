package ru.practicum.android.diploma.favorites.domain.models

import ru.practicum.android.diploma.db.entities.VacancyWithEmployer

sealed class FavoritesScreenState {
    data class Default(val vacancyList: List<VacancyWithEmployer>) : FavoritesScreenState() // доработать, когда будет готова база данных
    data object NoFavoritesAdded : FavoritesScreenState()
    data object LoadingError : FavoritesScreenState()

}
