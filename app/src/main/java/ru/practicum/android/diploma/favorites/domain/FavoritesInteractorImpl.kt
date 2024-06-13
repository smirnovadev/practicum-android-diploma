package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.db.entities.VacancyWithEmployer
import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState

class FavoritesInteractorImpl(private val favoritesRepository: FavoritesRepository) : FavoritesInteractor {
    override suspend fun loadData(): FavoritesScreenState {
        val vacanciesList = verificationData(favoritesRepository.loadData())
        return when {
            vacanciesList.isNullOrEmpty() -> {
                FavoritesScreenState.NoFavoritesAdded
            }

            vacanciesList.isNotEmpty() -> {
                FavoritesScreenState.Default(vacanciesList)
            }

            else -> FavoritesScreenState.LoadingError
        }
    }

    private fun verificationData(list: List<VacancyWithEmployer>): List<VacancyWithEmployer>? {
        return if (list.toString() == "[]") {
            null
        } else {
            list
        }
    }
}
