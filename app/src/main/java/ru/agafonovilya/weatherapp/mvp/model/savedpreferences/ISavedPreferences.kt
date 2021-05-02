package ru.agafonovilya.weatherapp.mvp.model.savedpreferences

import ru.agafonovilya.weatherapp.ui.sharedpreferences.SharedPrefValue

interface ISavedPreferences {
    fun putPreferences(key: String, value: String)
    fun getPreferences(key: String): String

    fun saveLightTheme()
    fun saveNightTheme()
    fun getSavedTheme(): SharedPrefValue
}