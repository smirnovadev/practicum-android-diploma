package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.db.entities.IndustryEntity

@Dao
interface IndustriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndustry(industry: IndustryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndustries(industries: List<IndustryEntity>)

    @Query("SELECT * FROM industries_table")
    suspend fun getIndustries(): List<IndustryEntity>

    @Query("SELECT id FROM industries_table")
    suspend fun getIndustryId(): List<Int>

    @Delete(entity = IndustryEntity::class)
    fun deleteIndustry(industryEntity: IndustryEntity)

}
