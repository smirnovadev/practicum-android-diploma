package ru.practicum.android.diploma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.db.dao.AreasDao
import ru.practicum.android.diploma.db.dao.IndustriesDao
import ru.practicum.android.diploma.db.dao.VacanciesDao
import ru.practicum.android.diploma.db.entities.AreaEntity
import ru.practicum.android.diploma.db.entities.IndustryEntity
import ru.practicum.android.diploma.db.entities.VacancyEntity

@Database(
    version = 1,
    entities = [VacancyEntity::class, AreaEntity::class, IndustryEntity::class],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacanciesDao(): VacanciesDao
    abstract fun industriesDao(): IndustriesDao
    abstract fun areasDao(): AreasDao
}
