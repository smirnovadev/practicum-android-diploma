package ru.practicum.android.diploma.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Drop the old table
                db.execSQL("DROP TABLE IF EXISTS vacancy_table")
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS favorite_vacancy_table (
                        id TEXT NOT NULL PRIMARY KEY,
                        address TEXT NOT NULL,
                        alternateUrl TEXT NOT NULL,
                        applyAlternateUrl TEXT NOT NULL,
                        area TEXT NOT NULL,
                        contacts TEXT NOT NULL,
                        description TEXT NOT NULL,
                        employer TEXT NOT NULL,
                        employment TEXT NOT NULL,
                        experience TEXT NOT NULL,
                        keySkills TEXT NOT NULL,
                        name TEXT NOT NULL,
                        salary TEXT NOT NULL,
                        schedule TEXT NOT NULL,
                    )
                """
                )
            }
        }
    }
}
