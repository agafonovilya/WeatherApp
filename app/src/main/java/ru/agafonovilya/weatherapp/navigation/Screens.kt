package ru.agafonovilya.weatherapp.navigation

import ru.agafonovilya.weatherapp.ui.fragments.DailyFragment
import ru.agafonovilya.weatherapp.ui.fragments.LocationFragment
import ru.agafonovilya.weatherapp.ui.fragments.MainScreenFragment
import ru.agafonovilya.weatherapp.ui.fragments.SettingsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class MainScreen: SupportAppScreen() {
        override fun getFragment() = MainScreenFragment.newInstance()
    }
    class LocationScreen: SupportAppScreen() {
        override fun getFragment() = LocationFragment.newInstance()
    }
    class DailyScreen(private val unixUTC: Int) : SupportAppScreen() {
        override fun getFragment() = DailyFragment.newInstance(unixUTC)
    }
    class SettingsScreen: SupportAppScreen() {
        override fun getFragment() = SettingsFragment.newInstance()
    }
}