package ru.agafonovilya.weatherapp.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.agafonovilya.weatherapp.mvp.model.entity.Daily
import ru.agafonovilya.weatherapp.mvp.model.entity.OneCallRequest

interface IWeatherCache {
    fun getWeather(): Single<OneCallRequest>
    fun getDayWeather(unixUTC: Int?): Single<Daily>
    fun putWeather(oneCallRequest: OneCallRequest): Completable
}