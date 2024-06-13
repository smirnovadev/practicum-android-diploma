package ru.practicum.android.diploma.favorites.data

import android.util.Log
import ru.practicum.android.diploma.db.AppDatabase
import ru.practicum.android.diploma.db.entities.VacancyWithEmployer
import ru.practicum.android.diploma.favorites.domain.FavoritesRepository

class FavoritesRepositoryImpl(private val appDatabase: AppDatabase) : FavoritesRepository {
    override suspend fun loadData(): List<VacancyWithEmployer> {
        val vacancyList = appDatabase.vacanciesDao().getVacanciesWithEmployer()
        Log.i("Vacancies_List", vacancyList.toString())
        return vacancyList
    }
}
