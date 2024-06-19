package ru.practicum.android.diploma.favorites.data

import ru.practicum.android.diploma.db.AppDatabase
import ru.practicum.android.diploma.favorites.domain.FavoritesRepository
import ru.practicum.android.diploma.job.domain.JobDbConvertor
import ru.practicum.android.diploma.search.domain.model.Vacancy

class FavoritesRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val convertor: JobDbConvertor
) : FavoritesRepository {
    override suspend fun loadData(): ArrayList<Vacancy> {
        val vacancyList = convertor
            .convertToListVacancy(appDatabase.vacanciesDao().getAllFavoriteVacancies())
        return vacancyList.toCollection(ArrayList())
    }
}
