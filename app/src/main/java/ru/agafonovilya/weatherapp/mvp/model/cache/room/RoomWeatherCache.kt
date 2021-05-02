package ru.agafonovilya.weatherapp.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.agafonovilya.weatherapp.mvp.model.cache.IWeatherCache
import ru.agafonovilya.weatherapp.mvp.model.entity.*
import ru.agafonovilya.weatherapp.mvp.model.entity.room.Database
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomCurrent
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomDaily
import ru.agafonovilya.weatherapp.mvp.model.entity.room.RoomHourly

class RoomWeatherCache(private val db: Database): IWeatherCache {
    override fun getWeather(): Single<OneCallRequest> {
        return Single.fromCallable {
            val oneCallRequest = OneCallRequest()
            oneCallRequest.current = Current()
            oneCallRequest.hourly = ArrayList()
            oneCallRequest.daily = ArrayList()

            val roomCurrent = db.currentDao.get()[0]
            val roomHourly: List<RoomHourly> = db.hourlyDao.getAll()
            val roomDaily: List<RoomDaily> = db.dailyDao.getAll()

            with(oneCallRequest.current!!) {
                dt = roomCurrent.dt
                temp = roomCurrent.temp
                weather?.get(0)?.description = roomCurrent.description
                weather?.get(0)?.icon = roomCurrent.icon
            }

            roomHourly.forEach {
                val hourlyWeather = HourlyWeather()
                hourlyWeather.icon = it.icon

                val hourly = Hourly()
                hourly.dt = it.dt
                hourly.temp = it.temp
                hourly.weather = mutableListOf(hourlyWeather)

                oneCallRequest.hourly!!.add(hourly)
            }

            roomDaily.forEach {
                val temp = Temp()
                temp.day = it.dayTemperature
                temp.night = it.nightTemperature
                temp.morning = it.morningTemperature
                temp.evening = it.eveningTemperature

                val dailyWeather = DailyWeather()
                dailyWeather.description = it.description
                dailyWeather.icon = it.icon

                val daily = Daily()
                daily.dt = it.dt
                daily.temp = temp
                daily.humidity = it.humidity
                daily.wind  = it.wind
                daily.weather = listOf(dailyWeather)

                oneCallRequest.daily!!.add(daily)
            }

            return@fromCallable oneCallRequest
        }
    }

    override fun putWeather(oneCallRequest: OneCallRequest): Completable {
        return Completable.fromAction{
            val roomHourly: MutableList<RoomHourly> = ArrayList()
            val roomDaily: MutableList<RoomDaily> = ArrayList()
            val roomCurrent = RoomCurrent(
                    dt = oneCallRequest.current?.dt,
                    temp = oneCallRequest.current?.temp,
                    description = oneCallRequest.current?.weather?.get(0)?.description,
                    icon = oneCallRequest.current?.weather?.get(0)?.icon)

            oneCallRequest.hourly?.forEach {
                roomHourly.add(RoomHourly(it.dt, it.temp, it.weather?.get(0)?.icon))
            }
            oneCallRequest.daily?.forEach{
                roomDaily.add(
                    RoomDaily(
                        dt = it.dt,
                        dayTemperature = it.temp?.day,
                        nightTemperature = it.temp?.night,
                        morningTemperature = it.temp?.morning,
                        eveningTemperature = it.temp?.evening,
                        humidity = it.humidity,
                        wind = it.wind,
                        description = it.weather?.get(0)?.description,
                        icon = it.weather?.get(0)?.icon)
                )
            }

            with(db.currentDao){
                delete()
                insert(roomCurrent)
            }

            db.hourlyDao.insert(roomHourly)
            db.dailyDao.insert(roomDaily)
        }.subscribeOn(Schedulers.io())
    }

    override fun getDayWeather(unixUTC: Int?): Single<Daily> {
        return Single.fromCallable {
            val daily = Daily()
            val roomDaily: RoomDaily = db.dailyDao.getByDt(unixUTC)

            with(roomDaily) {
                val temp = Temp()
                temp.day = dayTemperature
                temp.night = nightTemperature
                temp.morning = morningTemperature
                temp.evening = eveningTemperature

                val dailyWeather = DailyWeather()
                dailyWeather.description = description
                dailyWeather.icon = icon

                daily.dt = dt
                daily.temp = temp
                daily.humidity = humidity
                daily.wind  = wind
                daily.weather = listOf(dailyWeather)
            }

            return@fromCallable daily
        }
    }
}