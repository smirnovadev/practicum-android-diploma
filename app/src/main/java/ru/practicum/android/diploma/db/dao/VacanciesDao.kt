package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.db.entities.VacancyEntity

@Dao
interface VacanciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getVacancies(): List<VacancyEntity>

    @Query("SELECT id FROM vacancy_table")
    suspend fun getVacanciesId(): List<Int>

    @Delete(entity = VacancyEntity::class)
    fun deleteVacancy(vacancyEntity: VacancyEntity)

}
