package ru.agafonovilya.weatherapp.mvp.model.entity.room.dao

import androidx.room.*
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomHourly

@Dao
interface HourlyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hourly: RoomHourly)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg hourly: RoomHourly)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hourly: List<RoomHourly>)

    @Update
    fun update(hourly: RoomHourly)

    @Update
    fun update(vararg hourly: RoomHourly)

    @Update
    fun update(hourly: List<RoomHourly>)

    @Query("DELETE FROM RoomHourly")
    fun deleteAll()

    @Query("SELECT * FROM RoomHourly")
    fun getAll(): List<RoomHourly>
}