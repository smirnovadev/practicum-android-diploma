package ru.practicum.android.diploma.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity (
    @PrimaryKey
    val id: Int,


)
