package ru.agafonovilya.weatherapp.di.location

import dagger.Subcomponent
import ru.agafonovilya.weatherapp.di.location.module.LocationModule
import ru.agafonovilya.weatherapp.mvp.presenter.LocationPresenter

@LocationScope
@Subcomponent(modules = [
    LocationModule::class
])
interface LocationSubcomponent {
    fun inject(locationPresenter: LocationPresenter)
}