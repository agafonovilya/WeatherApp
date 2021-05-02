package ru.agafonovilya.weatherapp.mvp.view

import android.os.Parcelable
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface ISplashView: MvpView {
    fun startAnimation()
    fun startActivity(parcelable: Parcelable)
}