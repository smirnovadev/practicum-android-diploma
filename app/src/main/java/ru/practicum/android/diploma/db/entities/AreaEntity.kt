package ru.practicum.android.diploma.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "areas_table")
data class AreaEntity (
    @PrimaryKey
    val id: Int,
)
