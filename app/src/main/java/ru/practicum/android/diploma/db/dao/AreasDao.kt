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

    @Query("SELECT * FROM areas_table")
    suspend fun getAreas(): List<AreaEntity>

    @Query("SELECT id FROM areas_table")
    suspend fun getAreasId(): List<Int>

    @Delete(entity = AreaEntity::class)
    fun deleteArea(areaEntity: AreaEntity)

}
