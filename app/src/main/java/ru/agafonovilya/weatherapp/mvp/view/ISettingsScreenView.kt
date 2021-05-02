package ru.agafonovilya.weatherapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface ISettingsScreenView: MvpView {

    fun init()
    fun recreateActivity()
    fun checkLight()
    fun checkNight()
    fun animateView()
}