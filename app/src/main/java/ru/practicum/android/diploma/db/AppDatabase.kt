package ru.practicum.android.diploma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.practicum.android.diploma.db.dao.AreasDao
import ru.practicum.android.diploma.db.dao.FavoritesVacanciesDao
import ru.practicum.android.diploma.db.dao.IndustriesDao
import ru.practicum.android.diploma.db.entities.AreaEntity
import ru.practicum.android.diploma.db.entities.EmployerEntity
import ru.practicum.android.diploma.db.entities.IndustryEntity
import ru.practicum.android.diploma.db.entities.VacancyConverters
import ru.practicum.android.diploma.db.entities.VacancyEntity

@Database(
    version = 2,
    entities = [VacancyEntity::class, AreaEntity::class, IndustryEntity::class, EmployerEntity::class],
)
@TypeConverters(VacancyConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacanciesDao(): FavoritesVacanciesDao
    abstract fun industriesDao(): IndustriesDao
    abstract fun areasDao(): AreasDao
}
