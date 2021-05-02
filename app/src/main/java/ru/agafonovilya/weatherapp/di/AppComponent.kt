package ru.agafonovilya.weatherapp.di

import dagger.Component
import ru.agafonovilya.weatherapp.di.daily.DailySubcomponent
import ru.agafonovilya.weatherapp.di.location.LocationSubcomponent
import ru.agafonovilya.weatherapp.di.module.*
import ru.agafonovilya.weatherapp.mvp.presenter.MainPresenter
import ru.agafonovilya.weatherapp.mvp.presenter.MainScreenPresenter
import ru.agafonovilya.weatherapp.mvp.presenter.SettingsPresenter
import ru.agafonovilya.weatherapp.mvp.presenter.SplashPresenter
import ru.agafonovilya.weatherapp.ui.activity.MainActivity
import ru.agafonovilya.weatherapp.ui.activity.SplashActivity
import ru.agafonovilya.weatherapp.ui.adapter.CurrentAdapter
import ru.agafonovilya.weatherapp.ui.adapter.DailyRvAdapter
import ru.agafonovilya.weatherapp.ui.adapter.HourlyRvAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    CiceroneModule::class,
    DatabaseModule::class,
    ImageModule::class,
    RepoModule::class,
    SharedPrefModule::class
])
interface AppComponent {
    fun locationSubcomponent(): LocationSubcomponent
    fun dailySubcomponent(): DailySubcomponent

    fun inject(splashActivity: SplashActivity)
    fun inject(splashPresenter: SplashPresenter)

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainScreenPresenter: MainScreenPresenter)

    fun inject(hourlyRvAdapter: HourlyRvAdapter)
    fun inject(dailyRvAdapter: DailyRvAdapter)
    fun inject(currentAdapter: CurrentAdapter)

    fun inject(settingsPresenter: SettingsPresenter)
}