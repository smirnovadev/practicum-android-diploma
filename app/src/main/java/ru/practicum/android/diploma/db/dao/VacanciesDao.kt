package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.db.entities.VacancyEntity
import ru.practicum.android.diploma.db.entities.VacancyWithEmployer

@Dao
interface VacanciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getVacancies(): List<VacancyWithEmployer>

    @Query("SELECT id FROM vacancy_table WHERE id=:id")
    suspend fun getVacanciesById(id: String): List<VacancyWithEmployer>

    @Delete(entity = VacancyEntity::class)
    fun deleteVacancy(vacancyEntity: VacancyEntity)
}
