package ru.agafonovilya.weatherapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainScreenView: MvpView {
    fun init()
    fun getDataFromSplashActivity()
    fun updateCurrentView(temperature: Double, cityName: String, description: String, url: String)
    fun updateHourlyList()
    fun updateDailyList()
    fun setRefreshIcon(icon: Boolean)
}