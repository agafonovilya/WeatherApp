package ru.agafonovilya.weatherapp

import android.app.Application
import ru.agafonovilya.weatherapp.di.AppComponent
import ru.agafonovilya.weatherapp.di.DaggerAppComponent
import ru.agafonovilya.weatherapp.di.daily.DailySubcomponent
import ru.agafonovilya.weatherapp.di.location.LocationSubcomponent
import ru.agafonovilya.weatherapp.di.module.AppModule

class App: Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var locationSubcomponent: LocationSubcomponent? = null
        private set
    var dailySubcomponent: DailySubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun initLocationSubcomponent() = appComponent.locationSubcomponent().also { locationSubcomponent = it }
    fun releaseLocationSubcomponent() { locationSubcomponent = null}
    fun initDailySubcomponent() = appComponent.dailySubcomponent().also { dailySubcomponent = it }
    fun releaseDailySubcomponent() { dailySubcomponent = null}
}