package ru.agafonovilya.weatherapp.mvp.model.entity.room.dao

import androidx.room.*
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomDaily

@Dao
interface DailyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(daily: RoomDaily)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg daily: RoomDaily)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(daily: List<RoomDaily>)

    @Update
    fun update(daily: RoomDaily)

    @Update
    fun update(vararg daily: RoomDaily)

    @Update
    fun update(daily: List<RoomDaily>)

    @Query("DELETE FROM RoomDaily")
    fun deleteAll()

    @Query("SELECT * FROM RoomDaily")
    fun getAll(): List<RoomDaily>

    @Query("SELECT * FROM RoomDaily WHERE dt = :dt")
    fun getByDt(dt:Int?): RoomDaily
}