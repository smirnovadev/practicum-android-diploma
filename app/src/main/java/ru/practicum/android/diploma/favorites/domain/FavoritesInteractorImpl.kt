package ru.practicum.android.diploma.favorites.domain

import ru.practicum.android.diploma.favorites.domain.models.FavoritesScreenState
import ru.practicum.android.diploma.search.domain.model.Vacancy

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

    override suspend fun getFavVacancyById(id: String): Vacancy {
        return favoritesRepository.getFavVacancyById(id)
    }

    private fun verificationData(list: ArrayList<Vacancy>): ArrayList<Vacancy>? {
        return if (list.isEmpty()) {
            null
        } else {
            list
        }
    }
}
