package ru.agafonovilya.weatherapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IDailyScreenView: MvpView {
    fun init()
    fun updateWeatherView(unixUTC: Int,
                          iconURL: String,
                          description: String,
                          morningTemp: Double,
                          dayTemp: Double,
                          eveningTemp: Double,
                          nightTemp: Double,
                          wind: Double,
                          humidity: Int)
    fun release()
}