package ru.agafonovilya.weatherapp.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.agafonovilya.weatherapp.mvp.model.repo.IWeatherRepo
import ru.agafonovilya.weatherapp.mvp.model.savedpreferences.ISavedPreferences
import ru.agafonovilya.weatherapp.mvp.view.ISplashView
import ru.agafonovilya.weatherapp.ui.sharedpreferences.SharedPrefKeys
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter: MvpPresenter<ISplashView>() {

    @Inject lateinit var weatherRepo: IWeatherRepo
    @Inject lateinit var mainThreadScheduler: Scheduler
    @Inject lateinit var appLanguage: String
    @Inject lateinit var exclude: String
    @Inject lateinit var sharedPreferences: ISavedPreferences

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.startAnimation()
        loadWeather()
    }

    private fun loadWeather(){
        weatherRepo.getWeather(latitude = sharedPreferences.getPreferences(SharedPrefKeys.CURRENT_LATITUDE.key),
            longitude = sharedPreferences.getPreferences(SharedPrefKeys.CURRENT_LONGITUDE.key),
            exclude = exclude, appLanguage = appLanguage)
            .observeOn(mainThreadScheduler)
            .delay(1000, TimeUnit.MILLISECONDS)
            .subscribe({ it ->
                viewState.startActivity(it)
            }, {
                it.printStackTrace()
            }
            )
    }
}