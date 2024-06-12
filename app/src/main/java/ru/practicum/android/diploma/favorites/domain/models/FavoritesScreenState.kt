package ru.practicum.android.diploma.favorites.domain.models

sealed class FavoritesScreenState {
    data object Default : FavoritesScreenState() // доработать, когда будет готова база данных
    data object NoFavoritesAdded : FavoritesScreenState()
    data object LoadingError : FavoritesScreenState()

}
