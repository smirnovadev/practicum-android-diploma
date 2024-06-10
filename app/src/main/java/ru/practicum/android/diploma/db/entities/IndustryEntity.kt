package ru.practicum.android.diploma.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "industries_table")
data class IndustryEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
)
