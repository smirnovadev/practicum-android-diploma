package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.practicum.android.diploma.db.entities.VacancyEntity
import ru.practicum.android.diploma.db.entities.VacancyWithEmployer

@Dao
interface VacanciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Query("SELECT * FROM vacancy_table")
    @Transaction
    suspend fun getVacancies(): List<VacancyWithEmployer>

    @Query("SELECT * FROM vacancy_table WHERE id=:id")
    @Transaction
    suspend fun getVacanciesById(id: Int): List<VacancyWithEmployer>

    @Delete(entity = VacancyEntity::class)
    suspend fun deleteVacancy(vacancyEntity: VacancyEntity)
}
