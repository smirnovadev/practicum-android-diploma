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
    suspend fun insertVacancyWithEmployer(
        id: Int,
        name: String,
        address: String,
        salary: String,
        department: String,
        experience: String,
        workingDays: String,
        schedule: String,
        professionalRoles: String,
        type: String,
        area: String,
        description: String,
        keySkills: String,
        contacts: String,
        alternateUrl: String,
        employerId: Int,
        employerLogoUrls: String,
        employerName: String,
        employerUrl: String
    ) {
        insertEmployer(
            EmployerEntity(
                employerId,
                employerLogoUrls,
                employerName,
                employerUrl
            )
        )

        insertVacancy(
            VacancyEntity(
                id = id,
                name = name,
                address = address,
                salary = salary,
                department = department,
                experience = experience,
                workingDays = workingDays,
                schedule = schedule,
                professionalRoles = professionalRoles,
                type = type,
                area = area,
                description = description,
                keySkills = keySkills,
                contacts = contacts,
                alternateUrl = alternateUrl,
                employerId = employerId
            )
        )
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
