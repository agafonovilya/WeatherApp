package ru.agafonovilya.weatherapp.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ILocationScreenView: MvpView {
    fun init()
    fun initAutoCompeteTextView(citiesList: List<String>)
    fun release()
}