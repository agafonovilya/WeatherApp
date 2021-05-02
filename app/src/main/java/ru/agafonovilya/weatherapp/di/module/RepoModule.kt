package ru.agafonovilya.weatherapp.di.module

import dagger.Module
import dagger.Provides
import ru.agafonovilya.weatherapp.mvp.model.api.IDataSource
import ru.agafonovilya.weatherapp.mvp.model.cache.IWeatherCache
import ru.agafonovilya.weatherapp.mvp.model.cache.room.RoomWeatherCache
import ru.agafonovilya.weatherapp.mvp.model.entity.room.Database
import ru.agafonovilya.weatherapp.mvp.model.network.INetworkStatus
import ru.agafonovilya.weatherapp.mvp.model.repo.IWeatherRepo
import ru.agafonovilya.weatherapp.mvp.model.repo.retrofit.RetrofitWeatherRepo
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    fun weatherCache(database: Database): IWeatherCache {
        return RoomWeatherCache(database)
    }

    @Singleton
    @Provides
    fun weatherRepo(api: IDataSource,
                    networkStatus: INetworkStatus,
                    cache: IWeatherCache
    ): IWeatherRepo = RetrofitWeatherRepo(api, networkStatus, cache)
}