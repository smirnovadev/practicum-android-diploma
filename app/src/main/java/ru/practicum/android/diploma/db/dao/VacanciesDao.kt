package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.practicum.android.diploma.db.entities.EmployerEntity
import ru.practicum.android.diploma.db.entities.VacancyEntity
import ru.practicum.android.diploma.db.entities.VacancyWithEmployer

@Dao
interface VacanciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployer(vacancy: EmployerEntity)

    @Transaction
    suspend fun insertVacancyWithEmployer(vacancyWithEmployer: VacancyWithEmployer) {
        insertEmployer(vacancyWithEmployer.employer)
        insertVacancy(vacancyWithEmployer.vacancy)
    }

    @Query("SELECT * FROM vacancy_table")
    @Transaction
    suspend fun getVacanciesWithEmployer(): List<VacancyWithEmployer>

    @Query("SELECT * FROM vacancy_table WHERE id=:id")
    @Transaction
    suspend fun getVacanciesWithEmployerById(id: Int): List<VacancyWithEmployer>

    @Delete(entity = VacancyEntity::class)
    suspend fun deleteVacancy(vacancyEntity: VacancyEntity)
}
