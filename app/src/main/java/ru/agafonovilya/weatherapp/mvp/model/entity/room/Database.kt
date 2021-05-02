package ru.agafonovilya.weatherapp.mvp.model.entity.room

import androidx.room.RoomDatabase
import ru.agafonovilya.weatherapp.mvp.model.entity.room.dao.CurrentDao
import ru.agafonovilya.weatherapp.mvp.model.entity.room.dao.DailyDao
import ru.agafonovilya.weatherapp.mvp.model.entity.room.dao.HourlyDao
import ru.agafonovilya.weatherapp.mvp.model.entity.room.dao.ImageDao

@androidx.room.Database(entities = [RoomCurrent::class,
                                    RoomHourly::class,
                                    RoomDaily::class,
                                    RoomImage::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val currentDao: CurrentDao
    abstract val hourlyDao: HourlyDao
    abstract val dailyDao: DailyDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
    }
}