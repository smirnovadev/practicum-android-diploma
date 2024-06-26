package ru.practicum.android.diploma.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.db.entities.AreaEntity

@Dao
interface AreasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArea(area: AreaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreas(areas: List<AreaEntity>)

    @Query("SELECT * FROM areas_table")
    suspend fun getAreas(): List<AreaEntity>

    @Query("SELECT * FROM areas_table WHERE parent IS NULL")
    suspend fun getCountries(): List<AreaEntity>

    @Query("SELECT * FROM areas_table WHERE parent==:parent")
    suspend fun getRegions(parent: Int): List<AreaEntity>

    @Query("SELECT * FROM areas_table WHERE name LIKE '%' || :name || '%' AND parent=:parent")
    suspend fun getRegion(name: String, parent: Int): List<AreaEntity>

    @Delete(entity = AreaEntity::class)
    fun deleteArea(areaEntity: AreaEntity)
}
