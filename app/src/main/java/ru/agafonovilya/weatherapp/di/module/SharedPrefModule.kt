package ru.agafonovilya.weatherapp.di.module

import dagger.Module
import dagger.Provides
import ru.agafonovilya.weatherapp.mvp.model.savedpreferences.ISavedPreferences
import ru.agafonovilya.weatherapp.ui.sharedpreferences.SharedPreferences

@Module
class SharedPrefModule {
    @Provides
    fun getSharedPref(): ISavedPreferences = SharedPreferences()

}