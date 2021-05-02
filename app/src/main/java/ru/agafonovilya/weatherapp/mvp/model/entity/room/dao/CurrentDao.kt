package ru.agafonovilya.weatherapp.mvp.model.entity.room.dao

import androidx.room.*
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomCurrent

@Dao
interface CurrentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(current: RoomCurrent)

    @Update
    fun update(current: RoomCurrent)

    @Query("DELETE FROM RoomCurrent")
    fun delete()

    @Query("SELECT * FROM RoomCurrent")
    fun get(): List<RoomCurrent>
}