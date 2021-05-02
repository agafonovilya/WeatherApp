package ru.agafonovilya.weatherapp.di.daily

import dagger.Subcomponent
import ru.agafonovilya.weatherapp.di.daily.module.DailyModule
import ru.agafonovilya.weatherapp.mvp.presenter.DailyPresenter
import ru.agafonovilya.weatherapp.ui.adapter.DailyAdapter

@DailyScope
@Subcomponent(modules = [
   DailyModule::class
])
interface DailySubcomponent {
    fun inject(dailyPresenter: DailyPresenter)
    fun inject(dailyAdapter: DailyAdapter)
}
