package ru.agafonovilya.weatherapp.mvp.model.entity.room.dao

import androidx.room.*
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomImage

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<RoomImage>)

    @Update
    fun update(image: RoomImage)

    @Update
    fun update(vararg images: RoomImage)

    @Update
    fun update(images: List<RoomImage>)

    @Delete
    fun delete(image: RoomImage)

    @Delete
    fun delete(vararg images: RoomImage)

    @Delete
    fun delete(images: List<RoomImage>)

    @Query("DELETE FROM RoomImage")
    fun clear()

    @Query("SELECT * FROM RoomImage")
    fun getAll(): List<RoomImage>

    @Query("SELECT * FROM RoomImage WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomImage?
}