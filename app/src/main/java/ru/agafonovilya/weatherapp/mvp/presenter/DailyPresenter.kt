package ru.agafonovilya.weatherapp.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.agafonovilya.weatherapp.mvp.model.api.getIconUrl
import ru.agafonovilya.weatherapp.mvp.model.cache.IWeatherCache
import ru.agafonovilya.weatherapp.mvp.view.IDailyScreenView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DailyPresenter(val unixUTC: Int) : MvpPresenter<IDailyScreenView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var cache: IWeatherCache
    @Inject lateinit var mainThreadScheduler: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadFromCache()
    }

    private fun loadFromCache() {
        cache.getDayWeather(unixUTC)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThreadScheduler)
                .subscribe({
                            viewState.updateWeatherView(
                                    unixUTC = it.dt ?: 0,
                                    dayTemp = it.temp?.day ?: 0.0,
                                    nightTemp = it.temp?.night ?: 0.0,
                                    morningTemp = it.temp?.morning ?: 0.0,
                                    eveningTemp = it.temp?.evening ?: 0.0,
                                    humidity = it.humidity ?: 0,
                                    wind = it.wind ?: 0.0,
                                    description = it.weather?.get(0)?.description ?: "",
                                    iconURL = getIconUrl(it.weather?.get(0)?.icon ?: "")
                            )
                },{
                    println("Error: ${it.message}")
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}