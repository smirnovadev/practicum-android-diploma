package ru.practicum.android.diploma.db.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "employer_table")
data class EmployerEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("logo_urls") val logoUrls: String,
    val name: String,
    val url: String
)


data class VacancyWithEmployer(
    @Embedded val vacancy: VacancyEntity,
    @Relation(
        parentColumn = "employer_id",
        entityColumn = "id",
        entity = EmployerEntity::class
    )
    val employer: EmployerEntity
)
