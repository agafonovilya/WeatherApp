package ru.agafonovilya.weatherapp.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.agafonovilya.weatherapp.App
import ru.agafonovilya.weatherapp.mvp.model.entity.room.Database
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database =
            Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
                    .build()
}